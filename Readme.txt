Welcome to the DesignKeep web project organizer. 
This application was built by Brandon Harrison (bkh33) 
as a project for a Drexel University class (CS338 GUI).

To compile and run the program follow, simply run the ant buildfile at the command line 
by typing ant.

After the project is built, navigate to the dist folder that was created. An 
executable JAR file named DesignKeep.jar should be present. Run the executable 
JAR file by typing "java -jar DesignKeep.jar".

DesignKeep was created to be an organizer and reference of common web design assets. 
The three assets supported are colors, images (.png and .jpg), and files (.txt and .js). 
The view was purposefully created smaller so that a web designer may keep it open while 
working on their project in another window.

How to use DesignKeep:

Upon running the application, you are presented with the main project list window. 
To add a project, simply click the "Add" button underneath the list. 
Specify a name for the project, and you're good to go! Click "OK" to add the project 
to the list, click "Cancel" to exit back to the main window. If you would like to remove 
the project, click on the project, then click "Remove" underneath the list. Confirm the 
removal, and it will be done. Once you have a project list that is populated and you would 
like to open one,click on the project you would like to edit, and click "Open" which is 
underneath the list.

Once open, you can see that the project contains three lists of different objects: 
colors, images, and files. Let's start with colors.

Colors:
To add a new color, simply click "Add New" underneath the color list. A Jdialog will be presented 
with multiple ways to input a color. First, add a custom name to the color object you are creating 
by inputting the text into the color name field at the top. Next, you may choose one of three ways 
to input a color: RGB value, hexadecimal value, or selecting the color of a pixel in an image of your choice.

To add a color with RGB values, simply input the values of each into it's respective text field, 
then hit the "OK" button inline to the right of the fields. If it is not a valid RGB color value, 
the fields will erase existing text.

To add a color with hexadecimal values, simply input the value into the hexadecimal text field, 
then hit the "OK" button inline to the right of the field. If it is not a valid hexadecimal color 
value, the field will erase existing text.

To add a color by selecting the color of a pixel from an image, click on the "Choose Color From Image" button. 
A file explorer will open, and allow you to choose any JPEG or PNG image on your computer. Once selected, click 
"Open", and the image will be presented to you in a new JDialog window. Simply use your mouse and click anywhere 
on the image to obtain the color of that pixel. The background of the JDialog window will change to the color of 
the pixel so that you may better observe it. Once happy with the color, click "OK" and the RGB color values of 
the color chosen will be filled in. Give it a name and click "OK" next to the RGB text fields.

Once you have added a color, that color can be seen inside the Color list. To remove the color, click "Remove" under 
the list, and confirm the removal. To preview the color, click "Preview" under the list. A JDialog with a background 
of the chosen color will be presented. Click "OK" to exit out of the preview.

Images:
To add a new image, simply click "Add New" underneath the image list. A JDialog will be presented with a name and 
location field. First, add a custom name to the image object you are adding by inputting the text into the image 
name field at the top. Next, choose "Open Image..." to open a file chooser. Choose the image file from anywhere on 
your computer (must be PNG or JPEG). Then click "Open".

Once you have chosen the image file, the file location path will be added to the location path text field. If you have 
not entered a custom name, the name of the image file will be added to that field. Click "OK" to add the image to the 
list, or "Cancel" to cancel the image adding action and go back to the project view window.

Once you have added an image, that image can be seen inside the Image list. To remove it, click "Remove" under the list, 
and confirm the removal. To preview the image, click "Preview" under the list. A JDialog containing the chosen image will 
be presented. Click "OK" to exit the preview.

Files:
Files are added and viewed the same as an image. To add a new file, simply click "Add New" underneath the 
file list. A JDialog will be presented with a name and location field. First, add a custom name to the 
file object you are adding by inputting the text into the file name field at the top. Next, choose 
"Open File..." to open a file chooser. Choose the file from anywhere on your computer (must be .TXT or .JS). 
Then click "Open".

Once you have chosen the file, it's location path will be added to the location path text field. If you have 
not entered a custom name, the name of the file will be added to that field. Click "OK" to add the file to 
the list, or "Cancel" to cancel the file adding action and go back to the project view window.

Once you have added a file, it can be seen inside the File list. To remove it, click "Remove" under the list, 
and confirm the removal. To preview the file, click "Preview" under the list. A JDialog containing the chosen 
file's text will be presented. Click "OK" to exit the preview.
