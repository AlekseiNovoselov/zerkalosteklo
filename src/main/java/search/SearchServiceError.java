package search;

public enum SearchServiceError {
    ServerError {
        public String getMessage() {
            return "Internal server error";
        }
    };
    public abstract String getMessage();
}