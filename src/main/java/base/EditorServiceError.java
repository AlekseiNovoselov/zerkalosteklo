package base;

public enum EditorServiceError {
    ServerError {
        public String getMessage() {
            return "Internal server error";
        }
    }
}
