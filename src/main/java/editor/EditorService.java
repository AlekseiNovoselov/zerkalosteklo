package editor;
import base.EditorServiceResponse;
import java.io.IOException;

public interface EditorService {
    EditorServiceResponse cropImage(Image image) throws IOException;
}
