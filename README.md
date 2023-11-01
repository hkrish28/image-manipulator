<h3>Project Description</h3>
<ul>
<p>This is an image processing application that lets users load supported images of certain file formats and run certain operations on them. They can additionaly save the resulting images into the file system in any of supported file formats.</p>
</ul>

<h3>How to run</h3>
<ul>
<p>The program is run using the ProgramRunner class. Once run, input any of the valid command for the operations on your images.
To run the example script provided, once the program runner is running, input "run script.txt" in the command line.</p></ul>

<h3>Supported commands</h3>
<ul><b>load image-path image-name</b>: Load an image from the specified path and refer it to henceforth in the program by the given image name.

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

<b>run script-file</b>: Load and run the script commands in the specified file.

</ul>

<h3>All Classes, Interfaces used</h3>
<ul>
<h4>ProgramRunner</h4> 
<i>Class</i>
<ul>
<p>The sole responsibility of this class is to get the application running and let the controller take over the reins.</p></ul>
</br>

<h3><u>Controller Classes</u></h3>
<h4>ImageProcessingController</h4>
<i>Interface</i>
<ul>
<p>This controller interface lists down the public methods (which is currently one - to start execution of the program) which should be implemented by every concrete class for the interface.</p></ul>
</br>

<h4>ScriptController</h4>
<i>Class</i>
<ul>
<p>This concrete implementation of the controller interface supports a list of commands that can be inputted by the user. The user can either run commands from the console or can alterntively embed them in a script file and run the script file using the controller.</p></ul>
</br>

<h3><u>View Classes</u></h3>
<h4>View</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to represent a view that is used for displaying contents to the users.</p></ul>
</br>

<h4>ViewImpl</h4>
<i>Class</i>
<ul>
<p>This implementation of view is a simple view that only displays messages prompting users to enter input and for displaying the status of the operation.</p></ul>
</br>

<h3><u>Model Classes</u></h3>
<h4>FileFormatEnum</h4>
<i>Enum</i>
<ul>
<p>This enum lists the different file formats that the program supports for load and save operations.</p></ul>
</br>

<h4>FileHandlerProvider</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to provide a factory method for creating different implementations of the FileHandler interface based on the given filename which can be used to determine the file type.</p></ul>
</br>

<h4>FileHandlerProviderImpl</h4>
<i>Class</i>
<p>This class implements the FileHandlerProvider interface.
The class uses a map, FILE_FORMAT_ENUM_MAP, to store different FileHandler implementations. The keys in this map are values from the enum FileFormatEnum , and the values are the corresponding FileHandler objects. This allows for efficient retrieval of FileHandler implementations based on the file format.
</p>
</br>

<h4>FileHandler</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to handle file operations for images, specifically loading and saving images of certain file formats and image types.</p></ul>
</br>

<h4>PpmFileHandler</h4>
<i>Class</i>
<ul>
<p>This class implements the FileHandler interface and is specifically designed to handle loading and saving of RGB images in PPM format.</p></ul>
</br>

<h4>CommonFileHandler</h4>
<i>Class</i>
<ul>
<p>This class implements the FileHandler interface and is specifically designed to handle saving of RGB images in JPG and PNG formats.</p></ul>
</br>

<h4>ImageRepository</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to manage multiple images and perform operations on them. It provides the ability to tag an image with a particular name and save images that have been tagged. The controller is coupled to the model using only this interface.</p></ul>
</br>

<h4>ImageRepositoryImpl</h4>
<i>Class</i>
<ul><p>This class implements the ImageRepository interface.

The ImageRepositoryImpl class uses a Map data structure, imageMap, to store images. The keys in this
map are the names of the images, and the values are the actual Image objects. This allows for
efficient retrieval, addition, and removal of images based on their names.</p></ul>
</br>

<h4>ImageType</h4>
<i>Enum</i>
<ul><p>This enum lists the different types of images and provide some functions related to each specific image type. The only function that is currently implemented is for the enum to be able to create a new pixel for the particular image type. It has a public field that contains the color channels that are part of this image type and can be used to determine the order of the color channels in the pixel.
The ImageType enum currently has only one value, RGB, which represents an image that uses the red, green, and blue color channels.</p></ul>
</br>

<h4>Image</h4>
<i>Interface</i>
<ul>
<p>This image interface represents an image and the operations that can be performed on it.</p></ul>
</br>

<h4>ImagePixelImpl</h4>
<i>Class</i>
<ul><p>This class implements the Image interface and is designed to represent an image as a 2-D array of Pixel objects.
</br>

The ImagePixelImpl class has four instance variables: width, height, imageType, and pixels. The
width and height variables represent the dimensions of the image. The imageType variable is of type
ImageType, an enum that defines the different types of images that can be represented. The pixels
variable is a 2-D array that stores the Pixel objects that make up the image.</p>
</ul>
</br>

<h4>ImageConstants</h4>
<i>Class</i>
<ul>
<p>This class is designed to hold constants that are used for operations on different types of images.</p></ul>
</br>

<h4>ColorChannel</h4>
<i>Enum</i>
<ul>
<p>This enum lists the different color channels that a pixel can contain in an image.</p></ul>
</br>

<h4>Pixel</h4>
<i>Interface</i>
<ul>
<p>This interface is designed to represent a pixel in an image. A pixel is the smallest unit of an image and is composed of various color channels.</p></ul>
</br>

<h4>RgbPixel</h4>
<i>Class</i>
<ul><p>This class implements the Pixel interface and is specifically designed to represent a pixel in an RGB image.</p></ul>

</ul>