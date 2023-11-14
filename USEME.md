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

<b>compress percentage image-name dest-image-name</b>: Create a compressed version of an image. Percentages between 0 and 100 are considered valid.

<b>histogram image-name dest-image-name</b>: Produce an image that represents the histogram of the given image. The size of this image would be 256x256. It contains the histograms for the red, green and blue channels as line graphs.

<b>color-correct image-name dest-image-name</b>: Support the ability to color-correct an image by aligning the meaningful peaks of its histogram.

<b>levels-adjust b m w image-name dest-image-name</b>: This command can be used to adjust levels of an image where b, m and w are the three relevant black, mid and white values respectively. These values should be ascending in that order, and should be within 0 and 255 for this command to work correctly.

<b>run script-file</b>: Load and run the script commands in the specified file.

<b>exit</b>: Exit the execution of the program.

<h3>Note:</h3>
Some of the commands above support the ability to specify a vertical line to generate a split view of operations. The operations that support this are blur, sharpen, sepia, greyscale, color correction and levels adjustment. The script commands for these operations accommodates an optional parameter for the placement of the splitting line. For example, blur can be done by "blur image-name dest-image-name" or "blur image-name dest-image split p" in that order where 'p' is a percentage of the width (e.g. 50 means place the line halfway through the width of the image). The output image would contain only the relevant part suitably transformed, with the original image in the remaining part.

