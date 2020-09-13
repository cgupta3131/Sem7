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

void display()
{
    glClearColor (50.0,0.0,0.0,1.0); //color the background with red color
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	glRotated(cameraAngle, 0.0f, 0.0f, 0.0f);
	glTranslated(0.0f, 0.0f, -15.0f);

	glPushMatrix();
	glTranslated(1.0f, -3.0f, 0.0f);
	glScaled(12.0f, 12.0f, 12.0f);
	glRotated(currentAngle, 0.0f, 1.0f, 0.0f);

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
    glFlush(); 
	glPopMatrix();
	glutSwapBuffers();
}

void updateAngle(int value)
{
	//rotate the bunny object by 4 degrees each time
	currentAngle += 4;

	glutPostRedisplay();
	glutTimerFunc(25, updateAngle, 0);
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
	glutReshapeFunc(handleResize);

	glutTimerFunc(20, updateAngle, 0);
	glutMainLoop();
}