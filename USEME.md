<h3>Supported commands</h3>

<ul>
<b>load image-path image-name</b>: Load an image from the specified path and refer it to henceforth in the program by the given image name.
<p><i>Example</i>: load resources/bird.jpg bird</p>
<p><i>Condition:</i>
<ul><li>The image format must be supported by the application. Supported formats are currently jpg, png and ppm</li></ul></p>

<b>save image-path image-name</b>: Save the image with the given name to the specified path which
should include the name of the file.
<p><i>Example</i>: save resources/bird.jpg bird</p>
<p><i>Condition</i>: 
<ul>
<li>The image to be saved must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
<li>The image format must be supported by the application. Supported formats are currently jpg, png and ppm</li>
</ul></p>

<b>red-component image-name dest-image-name</b>: Create an image with the red-component of the image
with the given name, and refer to it henceforth in the program by the given destination name.
Similar commands for green, blue, value, luma, intensity components should be supported. Note that
the images for value, luma and intensity will be greyscale images.
<p><i>Example</i>: red-component bird bird-red</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
</ul></p>

<b>horizontal-flip image-name dest-image-name</b>: Flip an image horizontally to create a new image,
referred to henceforth by the given destination name.
<p><i>Example</i>: horizontal-flip bird bird-horizontal-flip</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
</ul></p>

<b>vertical-flip image-name dest-image-name</b>: Flip an image vertically to create a new image,
referred to henceforth by the given destination name.
<p><i>Example</i>: vertical-flip bird bird-vertical-flip</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
</ul></p>

<b>brighten increment image-name dest-image-name</b>: brighten the image by the given increment to
create a new image, referred to henceforth by the given destination name. The increment may be
positive (brightening) or negative (darkening).
<p><i>Example</i>: brighten 20 bird bird-brighter</p>
<p><i>Example 2</i>: brighten -20 bird bird-darker</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
</ul></p>

<b>rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue</b>: split
the given image into three images containing its red, green and blue components respectively. These
would be the same images that would be individually produced with the red-component, green-component
and blue-component commands.
<p><i>Example</i>: rgb-split bird bird-red bird-green bird-blue</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
</ul></p>

<b>rgb-combine image-name red-image green-image blue-image</b>: Combine the three greyscale images
into a single image that gets its red, green and blue components from the three images respectively.
<p><i>Example</i>: rgb-combine bird bird-red bird-green bird-blue</p>
<p><i>Condition</i>: 
<ul>
<li>The source images(note that there are three here, namely bird-red, bird-green, and bird-blue) must be present in the application, which implies the 
presence of at least three commands(of which at least one is load) which creates the source images for this command.</li>
</ul></p>

<b>blur image-name dest-image-name</b>: blur the given image and store the result in another image
with the given name.
<p><i>Example</i>: blur bird bird-blur</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
</ul></p>

<b>sharpen image-name dest-image-name</b>: sharpen the given image and store the result in another
image with the given name.
<p><i>Example</i>: sharpen bird bird-sharp</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
</ul></p>

<b>sepia image-name dest-image-name</b>: produce a sepia-toned version of the given image and store
the result in another image with the given name.
<p><i>Example</i>: sepia bird bird-sepia</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
</ul></p>

<b>compress percentage image-name dest-image-name</b>: Create a compressed version of an image.
Percentages between 0 and 100 are considered valid.
<p><i>Example</i>: compress 10 bird bird-compressed</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
<li>The compression value is a percentage and should be between 0 and 100 (both inclusive)</li>
</ul></p>

<b>histogram image-name dest-image-name</b>: Produce an image that represents the histogram of the
given image. The size of this image would be 256x256. It contains the histograms for the red, green
and blue channels as line graphs.
<p><i>Example</i>: histogram bird bird-histogram</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
</ul></p>

<b>color-correct image-name dest-image-name</b>: Support the ability to color-correct an image by
aligning the meaningful peaks of its histogram.
<p><i>Example</i>: color-correct bird bird-color-correct</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
</ul></p>

<b>levels-adjust b m w image-name dest-image-name</b>: This command can be used to adjust levels of
an image where b, m and w are the three relevant black, mid and white values respectively. These
values should be ascending in that order, and should be within 0 and 255 for this command to work
correctly.
<p><i>Example</i>: levels-adjust 12 140 244 bird bird-level-adjust</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
<li>The b, m, and w values should be ascending in that order, and should be within 0 and 255</li>
</ul></p>

<b>run script-file</b>: Load and run the script commands in the specified file.
<p><i>Example</i>: run example_script.txt</p>
<p><i>Condition</i>: 
<ul>
<li>The source file should be present in the File System at the specified location.</li>
</ul></p>

<b>exit</b>: Exit the execution of the program.
<p><i>Example</i>: exit</p>

<h3>Note:</h3>
Some of the commands above support the ability to specify a vertical line to generate a split view
of operations. The operations that support this are blur, sharpen, sepia, greyscale(all three), color
correction and levels adjustment. The script commands for these operations accommodates an optional
parameter for the placement of the splitting line. For example, blur can be done by "blur image-name
dest-image-name" or "blur image-name dest-image split p" in that order where 'p' is a percentage of
the width (e.g. 50 means place the line halfway through the width of the image). The output image
would contain only the relevant part suitably transformed, with the original image in the remaining
part.
<p><i>Example</i>: color-correct bird bird-color-correct split 20</p>
<p><i>Condition</i>: 
<ul>
<li>The source image must be present in the application, which implies the 
presence of at least one load command (and usually other commands as well) before this command.</li>
<li>The split percentage should be between 0 and 100 (inclusive).</li>
</ul></p>
</ul>