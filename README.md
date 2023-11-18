<h3>Project Description</h3>
<ul>
<p>This is an image processing application that lets users load supported images of certain file formats and run certain operations on them. They can additionaly save the resulting images into the file system in any of supported file formats.</p>
</ul>

<h3>How to run</h3>
<ul>
<p>The program is run using the ProgramRunner class. This class can be run without any arguments which once run, will prompt user to input any of the valid command for the operations on images.
To run the example script provided in this way, once the program runner is running, input "run res/script.txt" in the command line and after the execution of the script, type "exit" to stop the program.</p>
<p>The class can also be provided a command line argument specifying the script file that contains the commands to be run. For that use the command line arguments '-f <i>filename</i>'. To run the example script provided this way,
run the ProgramRunner class with '-f res/script.txt' as command line argument.
<p>To run the program from the command line using the JAR file provided, run 
<b>java -jar image-manipulator.jar -f script-jar.txt</b> after <i>cd</i>-ing into the res/ folder in the project.
Ensure that the images and results folders are present in res root along with the jar as the operations are performed
using the images present in the 'images' folder, and saved into 'results' folder.
</p></ul>

<h3>Supported commands</h3>

<ul>
<b>load image-path image-name</b>: Load an image from the specified path and refer it to henceforth in the program by the given image name.

<b>save image-path image-name</b>: Save the image with the given name to the specified path which
should include the name of the file.

<b>red-component image-name dest-image-name</b>: Create an image with the red-component of the image
with the given name, and refer to it henceforth in the program by the given destination name.
Similar commands for green, blue, value, luma, intensity components should be supported. Note that
the images for value, luma and intensity will be greyscale images.

<b>horizontal-flip image-name dest-image-name</b>: Flip an image horizontally to create a new image,
referred to henceforth by the given destination name.

<b>vertical-flip image-name dest-image-name</b>: Flip an image vertically to create a new image,
referred to henceforth by the given destination name.

<b>brighten increment image-name dest-image-name</b>: brighten the image by the given increment to
create a new image, referred to henceforth by the given destination name. The increment may be
positive (brightening) or negative (darkening).

<b>rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue</b>: split
the given image into three images containing its red, green and blue components respectively. These
would be the same images that would be individually produced with the red-component, green-component
and blue-component commands.

<b>rgb-combine image-name red-image green-image blue-image</b>: Combine the three greyscale images
into a single image that gets its red, green and blue components from the three images respectively.

<b>blur image-name dest-image-name</b>: blur the given image and store the result in another image
with the given name.

<b>sharpen image-name dest-image-name</b>: sharpen the given image and store the result in another
image with the given name.

<b>sepia image-name dest-image-name</b>: produce a sepia-toned version of the given image and store
the result in another image with the given name.

<b>compress percentage image-name dest-image-name</b>: Create a compressed version of an image.
Percentages between 0 and 100 are considered valid.

<b>histogram image-name dest-image-name</b>: Produce an image that represents the histogram of the
given image. The size of this image would be 256x256. It contains the histograms for the red, green
and blue channels as line graphs.

<b>color-correct image-name dest-image-name</b>: Support the ability to color-correct an image by
aligning the meaningful peaks of its histogram.

<b>levels-adjust b m w image-name dest-image-name</b>: This command can be used to adjust levels of
an image where b, m and w are the three relevant black, mid and white values respectively. These
values should be ascending in that order, and should be within 0 and 255 for this command to work
correctly.

<b>run script-file</b>: Load and run the script commands in the specified file.

<b>exit</b>: Exit the execution of the program.

<h3>Note:</h3>
Some of the commands above support the ability to specify a vertical line to generate a split view
of operations. The operations that support this are blur, sharpen, sepia, greyscale, color
correction and levels adjustment. The script commands for these operations accommodates an optional
parameter for the placement of the splitting line. For example, blur can be done by "blur image-name
dest-image-name" or "blur image-name dest-image split p" in that order where 'p' is a percentage of
the width (e.g. 50 means place the line halfway through the width of the image). The output image
would contain only the relevant part suitably transformed, with the original image in the remaining
part.

</ul>

<h3>All Classes, Interfaces used</h3>
<ul>
<h4>ProgramRunner</h4> 
<i>Class</i>
<ul>
<p>The sole responsibility of this class is to get the application running and let the controller take over the reins.
</p></ul>
<br>

<h3><u>Controller Classes</u></h3>
<h4>ImageProcessingController</h4>
<i>Interface</i>
<ul>
<p>This controller interface lists down the public methods (which is currently one - to start execution of the program) which should be implemented by every concrete class for the interface.
</p></ul>
<br>

<h4>ControllerImpl</h4>
<i>Class</i>
<ul>
<p>This concrete implementation of the controller interface supports a list of commands that can be inputted by the user. The user can either run commands from the console or can alterntively embed them in a script file and run the script file using the controller.
</p></ul>
<br>

<h4>FileFormatEnum</h4>
<i>Enum</i>
<ul>
<p>This enum lists the different file formats that the program supports for load and save operations.</p></ul>
<br>

<h4>FileHandlerProvider</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to provide a factory method for creating different implementations of the FileHandler interface based on the given filename which can be used to determine the file type.</p></ul>
<br>

<h4>FileHandlerProviderImpl</h4>
<i>Class</i>
<ul><p>This class implements the FileHandlerProvider interface.
The class uses a map, FILE_FORMAT_ENUM_MAP, to store different FileHandler implementations. The keys in this map are values from the enum FileFormatEnum , and the values are the corresponding FileHandler objects. This allows for efficient retrieval of FileHandler implementations based on the file format.
</p></ul>
<br>

<h4>FileHandler</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to handle file operations for images, specifically loading and saving images of certain file formats and image types.
</p></ul>
<br>

<h4>PpmFileHandler</h4>
<i>Class</i>
<ul>
<p>This class implements the FileHandler interface and is specifically designed to handle loading and saving of RGB images in PPM format.
</p></ul>
<br>

<h4>CommonFileHandler</h4>
<i>Class</i>
<ul>
<p>This class implements the FileHandler interface and is specifically designed to handle saving of RGB images in JPG and PNG formats.
</p></ul>
<br>

<h4>ImageHandler</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to provide methods for converting an image into a 3D array of its 
pixel values and vice versa. A pixel value is represented as an array of each of its color channels.
The ImageHandler interface can be used with any type of image object. The type of the image object is 
represented by the type parameter T.
</p></ul>

<h4>BufferedImageHandler</h4>
<i>Class</i>
<ul>
<p>This class implements the ImageHandler interface for BufferedImage objects, providing methods to 
convert between a BufferedImage and a 3D array of floating-point pixel values.
</p></ul>

<h4>ImageDrawer</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to provide methods for drawing on an image and retrieving the image data.
The ImageDrawer could act as an adapter for other libraries that provides implementations for the methods 
and can bring down the dependency on any such libraries for the clients of the libraries by utilizing this interface.
</p></ul>

<h4>ImageDrawerImpl</h4>
<i>Class</i>
<ul>
<p>This class implements the ImageDrawer interface and provides methods for drawing on an image 
and retrieving the image data. 
This implementation of the ImageDrawer class utilizes the BufferedImage class and the methods provided 
by Graphics class for performing the drawing functions on the image. Clients of this class do not have
to depend on BufferedImage class or awt package as the getImageDrawing method will finally return the
drawn image as a float[][][] object.
</p></ul>

<h4>Command</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to represent a command that is supported by the application. 
It provides a method, go, that will be implemented by each supported command in the application.
</p></ul>

<h4>AbstractCommand</h4>
<i>Abstract Class</i>
<ul>
<p>This abstract class implements the Command interface and provides a common structure for all 
command classes that will extend it.
</p></ul>

<h4>RedComponent</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that extracts the red component from an image.
</p></ul>

<h4>GreenComponent</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that extracts the green component from an image.
</p></ul>

<h4>BlueComponent</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that extracts the blue component from an image.
</p></ul>

<h4>Blur</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that applies a blur effect to an image.
</p></ul>

<h4>Sharpen</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that applies a sharpen effect to an image.
</p></ul>

<h4>Brighten</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that brightens an image.
</p></ul>

<h4>ColorCorrect</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that applies color correction on an image.
</p></ul>

<h4>Combine</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that combines images using their respective color channels.
</p></ul>

<h4>Compress</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that compresses an image.
</p></ul>

<h4>HorizontalFlip</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that flips an image horizontally.
</p></ul>

<h4>VerticalFlip</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that flips an image vertically.
</p></ul>

<h4>Histogram</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that generates a histogram from an image.
</p></ul>

<h4>RgbSplit</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that splits an image into its red, green, and blue color channels.
</p></ul>

<h4>Sepia</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that applies a sepia effect to an image.
</p></ul>

<h4>LumaGreyscale</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that converts an image to its luma greyscale.
</p></ul>

<h4>ValueGreyscale</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that converts an image to its value greyscale.
</p></ul>

<h4>IntensityGreyscale</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that converts an image to its intensity greyscale.
</p></ul>

<h4>LevelsAdjust</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that adjusts the levels of an image.
</p></ul>

<h4>Load</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that loads an image into the application.
</p></ul>

<h4>Run</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that runs a script file.
</p></ul>

<h4>Save</h4>
<i>Class</i>
<ul>
<p>This class extends the AbstractCommand class and represents a specific command that saves an image from the application to a file.
</p></ul>

<h3><u>View Classes</u></h3>
<h4>View</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to represent a view that is used for displaying contents to the users.</p></ul>
<br>

<h4>ViewImpl</h4>
<i>Class</i>
<ul>
<p>This implementation of view is a simple view that only displays messages prompting users to enter input and for displaying the status of the operation.</p></ul>
<br>

<h3><u>Model Classes</u></h3>

<h4>ImageRepository</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to manage multiple images and perform operations on them. It provides the ability to tag an image with a particular name and save images that have been tagged. The controller is coupled to the model using only this interface.</p></ul>
<br>

<h4>ImageRepositoryImpl</h4>
<i>Class</i>
<ul><p>This class implements the ImageRepository interface.

The ImageRepositoryImpl class uses a Map data structure, imageMap, to store images. The keys in this
map are the names of the images, and the values are the actual Image objects. This allows for
efficient retrieval, addition, and removal of images based on their names.
</p></ul>
<br>

<h4>ImageType</h4>
<i>Enum</i>
<ul><p>This enum lists the different types of images and provide some functions related to each specific image type. The only function that is currently implemented is for the enum to be able to create a new pixel for the particular image type. It has a public field that contains the color channels that are part of this image type and can be used to determine the order of the color channels in the pixel.
The ImageType enum currently has only one value, RGB, which represents an image that uses the red, green, and blue color channels.</p></ul>
<br>

<h4>Image</h4>
<i>Interface</i>
<ul>
<p>This image interface represents an image and the operations that can be performed on it.</p></ul>
<br>

<h4>ImagePixelImpl</h4>
<i>Class</i>
<ul><p>This class implements the Image interface and is designed to represent an image as a 2-D array of Pixel objects.
<br>

The ImagePixelImpl class has four instance variables: width, height, imageType, and pixels. The
width and height variables represent the dimensions of the image. The imageType variable is of type
ImageType, an enum that defines the different types of images that can be represented. The pixels
variable is a 2-D array that stores the Pixel objects that make up the image.</p>
</ul>
<br>

<h4>ImageConstants</h4>
<i>Class</i>
<ul>
<p>This class is designed to hold constants that are used for operations on different types of images.</p></ul>
<br>

<h4>ColorChannelEnum</h4>
<i>Enum</i>
<ul>
<p>This enum lists the different color channels that a pixel can contain in an image.</p></ul>
<br>

<h4>Pixel</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to represent a pixel in an image. A pixel is the smallest unit of an image and is composed of various color channels.
</p></ul>
<br>

<h4>RgbPixel</h4>
<i>Class</i>
<ul><p>This class implements the Pixel interface and is specifically designed to represent a pixel in an RGB image.
</p></ul>
<br>

<h4>Histogram</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to represent a histogram of an image. 
</p></ul>
<br>

<h4>HistogramImpl</h4>
<i>Class</i>
<ul>
<p>This class implements the Histogram interface and provides methods to generate and analyze histograms
based on the pixel values of an Image object of the model. 
</p></ul>
<br>

<h4>HistogramDrawer</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to provide a method for visualizing a histogram.
</p></ul>
<br>

<h4>HistogramDrawerImpl</h4>
<i>Class</i>
<ul>
<p>This class implements the HistogramDrawer interface and provides a method for visualizing a histogram.
This implementation of HistogramDrawer takes in an object of ImageDrawer as an argument and draws lines
between the frequencies of adjacent pixel values for each color channel of the histogram using the ImageDrawer object.
</p></ul>
<br>
</ul>


<h3>Citations</h3>
<ul><h4>Images Used</h4>
    <ul><li>bird : https://pixabay.com/photos/bird-avian-ornithology-nature-8311912/
    <p>Content License Summary: https://pixabay.com/service/license-summary/</p></li></ul>
</ul>

<h3>Changes to design and feature additions made</h3>

<ul>
<h3><i>Changes made:</i></h3>
<br>
<b>Controller now implements Command Design Pattern removing the switch case pattern</b>
<ul>Considering how the controller class is expected to grow for each added feature/command, the 
decision was taken to implement Command Design pattern and moving the logic for each of the command
into small classes of their own. By making use of a Map that maps a command to the appropriate
command object that performs the action that the helper method previously used to, the updates to 
the controller for additional command has been minimized (currently, a single line to add the 
command to map - which could also be moved out of the controller if need be).</ul>

<b>Moved FileHandler and related classes(FileFormatEnum, FileHandlerProvider and the implementations of the interfaces) to controller package</b>
<ul>Considering all I/O could be kept in controller for a better design and to keep model clear of any and all such operations relating to file system, the decision was taken to move the package to controller package. 
<p>Since FileHandler is no longer part of the model, the saveImage method now accepts image in the form of float[][][] instead of Image, to remove the coupling between model and FileHandler and also
to make it more generic in nature.</p></ul>
<b>Updates to existing ImageRepository interface methods</b>
<ul>Since FileHandler was moved out to controller, ImageRepository no longer takes in fileName for 
loading or saving, rather, the loadImage method now takes in image in the form of float[][][] and 
the name for the image to be loaded. For the same reason, saveImage has now been updated to getImage
which takes in the imageName to be retrieved and returns the image in the form of float[][][]. 
The section where load and save happens in the controller has changed accordingly and 
utilizes FileHandler(now in controller package) to perform the File System operations before/after
making the method calls(loadImage and getImage) to ImageRepository.</ul>

<b>Added a private helper to perform the greyscale functions inside ImagePixelImpl</b>
<ul>Since the implementation of methods for obtaining Luma, Value and Intensity greyscale images 
differed only by one line of code, a private helper that took in a Function object was introduced
to remove duplicity of code.</ul>

<b>Changing access modifiers to the most narrow scope that is possible.</b>
<ul>To perform adequate data hiding, changes were made across fields in the project to ensure that
they were given the most narrow scope</ul>

<b>ImageType now makes use of unmodifiable list to store the list of ColorChannel enum object to denote the order of color channels in the image type.
<ul>This is to ensure that the values cannot be modified from outside the class and for it to remain unmodfiable.</ul>

<b></b>
<ul></ul>
<h3><i>Features added:</i></h3>
<b>Support for new command : Histogram</b>
<ul>This command will allow users to create a histogram for an image which would be represented as an image as well(as line graph that connects adjacent pixel values frequencies for each of the red, green and blue color channels). An ImageDrawer interface was introduced in the controller package which has currently been implemented in ImageDrawerImpl reusing the BufferedImage class and its related methods to provide the drawing capabilities. For the histogram creation, controller passes an ImageDrawer object to the model to give it the capability of drawing on an image as it wishes. This ensures that the model is in no way coupled to the awt package that provides the BufferedImage class but is only dependent on an Interface defined in the application.</ul>
<b>Support for new commands : Color Correction, Levels Adjust </b>
<ul>The addition of the histogram capability brought about these other related features. The command color-correct will allow users to perform color correction on a given image and the command levels-adjust will allow users to adjust the levels of an image given the black, mid, and white values. </ul>
<b>Support for new command : Compression </b>
<ul>This command allows users to compress a given image and provide a compression percentage that determines how much the given image should be compressed. Values between 0 and 100 can be provided, with 0 doing no compression and 100 compressing the image into nothing but a black image of the given image's height and width. The compression utilizes Haar transform algorithm.</ul>
<b>Support for adding optional parameters to preview the output of a command </b>
<ul>Some operations(command) can be previewed now by splitting the image into 2 parts that contains the image after the given operation on the left part and the original image on the right part. Commands that support these features will take in the parameter 'split x' where x is a number between 0 to 100 which determines where the splitting occurs (value of 50 will split the image into 2 halves, other numbers will slide the vertical splitter accordingly).
<p>The commands that currently support this feature are blur, luma-component, value-component, intensity-component, sepia, color-correct, levels-adjust, and sharpen. Any of these commands can be substituted in place of 'command' in the below usage description. The number parameters are only for the commands that require them such as levels-adjust that takes 3 numbers as parameters. </ul>
<p><i>Usage: command [number params if any] src-image dest-image</i>
</ul>

