#include <iostream>
#include <vector>
#include <fstream>
#include <GL/glut.h>

using namespace std;

string filename = "lowpolybunny.obj";
char currentMode;
vector<GLdouble> vectorX, vectorY, vectorZ;
GLdouble xVer, yVer, zVer;
double currentAngle = 0.0;
double cameraAngle = 5.0;
float sdepth = -30.0;
float sphi=0.0, stheta=0.0;

int downX, downY;

bool leftButton = false, rightButton = false;

void handleResize(int w, int h)
{
	glViewport(0, 0, w, h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45.0, double(w) / double(h), 1.0, 200.0);
}

void handleEscKeyPress(unsigned char key, int x, int y)
{
	switch (key)
	{
		case 27:
			exit(0);
	}
}

void drawWireFrame()
{
	ifstream filePointer;
	filePointer.open(filename);
	if (!filePointer)
	{
		cout << "Not able to open the file " << filename << endl;
		return;
	}

    glColor3d(1,1,0); //color the model with yellow  
	while (filePointer >> currentMode)
	{
		filePointer >> xVer >> yVer >> zVer;
		switch (currentMode)
		{
			case 'v': //if the mode is vertex, push them into vectors
			{
				vectorX.push_back(xVer);
				vectorY.push_back(yVer);
				vectorZ.push_back(zVer);
			}
			break;
			case 'f': //if the mode is face, draw the face on the windoe
			{
				glBegin(GL_POLYGON);
				glVertex3d(vectorX[xVer - 1], vectorY[xVer - 1], vectorZ[xVer - 1]);
				glVertex3d(vectorX[yVer - 1], vectorY[yVer - 1], vectorZ[yVer - 1]);
				glVertex3d(vectorX[zVer - 1], vectorY[zVer - 1], vectorZ[zVer - 1]);
				glEnd();
			}
			break;
		}
	}

	glEnd(); 
}

void MouseCallback(int button, int state, int x, int y) {

  	downX = x; downY = y;

  	leftButton = ((button == GLUT_LEFT_BUTTON) && (state == GLUT_DOWN));

  	rightButton = ((button == GLUT_RIGHT_BUTTON) &&  (state == GLUT_DOWN));

  	glutPostRedisplay();
} 

void MotionCallback(int x, int y) {

  	if (leftButton) //rotate
  	{
	  	sphi -= (float)(x-downX)/4.0;
	  	stheta -= (float)(downY-y)/4.0;
	}

  	if (rightButton) //zooming
	{
		sdepth += (float)(downY - y) / 10.0;  
	}

  	downX = x;   downY = y;

  	glutPostRedisplay();
}

void display()
{
    glClearColor (50.0,0.0,0.0,1.0); //color the background with red color
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	glTranslatef(0.0, 0.0, sdepth);
	glRotatef(-stheta, 1.0, 0.0, 0.0);
    glRotatef(sphi, 0.0, 1.0, 0.0);
	glPushMatrix();
	glTranslated(1.0f, -3.0f, 0.0f);
	glScaled(20.0f, 20.0f, 20.0f);
	drawWireFrame();
	
    glFlush(); 
	glPopMatrix();
	glutSwapBuffers();
}

int main(int argc, char **argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_DEPTH | GLUT_RGB);
	glutInitWindowPosition(0, 0); //starting coordinates of the window
	glutInitWindowSize(1280, 720); //starting size of the window 
	glutCreateWindow("Bunny 3D Model"); //Name of the window given
	glEnable(GL_DEPTH_TEST);

	glutDisplayFunc(display);
	glutKeyboardFunc(handleEscKeyPress);
	glutMouseFunc(MouseCallback);
	glutMotionFunc(MotionCallback); 
	glutReshapeFunc(handleResize);

	glutMainLoop();
}