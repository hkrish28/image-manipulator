package ime;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * This implementation of the PhysicsBallProvider uses a static map to keep track of the specific
 * {@link FileHandler} implementations to be used for specific input string.
 */
public class FileHandlerProviderImpl implements FileHandlerProvider {

  private static final Map<FileFormatEnum, FileHandler> FILE_FORMAT_ENUM_MAP =
          generateFileHandlerClassMap();

  private static Map<FileFormatEnum, FileHandler> generateFileHandlerClassMap() {
    Map<FileFormatEnum, FileHandler> fileHandlers = new HashMap<>();
    fileHandlers.put(FileFormatEnum.ppm, new PpmFileHandler());
    fileHandlers.put(FileFormatEnum.jpeg, new JpgFileHandler());
    fileHandlers.put(FileFormatEnum.png, new PngFileHandler());
    return fileHandlers;
  }

  /**
   * Checks with the enum map of the class to map the input string to an implementation of
   * {@link FileHandlerProvider} class.
   *
   * @param ballType Input determining the type of PhysicsBall to be returned
   * @return object of physics ball
   */
  public FileHandler getFileHandler(String ballType) {
    FileFormatEnum type = validateBallType(ballType);
    return FILE_FORMAT_ENUM_MAP.get(type);
  }

  private FileFormatEnum validateBallType(String ballType) {
    try {
      return FileFormatEnum.valueOf(ballType);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid file format provided.");
    }
  }
}
