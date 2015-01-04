package editor;

import org.json.JSONObject;

public class Image {
    private Integer resultX;
    private Integer resultY;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private String originPath;

    public Image(Integer _resultX, Integer _resultY, Integer _x, Integer _y, Integer _width, Integer _height, String _originPath) {
        resultX = _resultX; resultY = _resultY; x = _x; y = _y; width = _width; height =_height; originPath = _originPath;
    }

    public Image(JSONObject jsonObj) {
        resultX = jsonObj.getInt("resultX");
        resultY = jsonObj.getInt("resultY");
        x = jsonObj.getInt("x");
        y = jsonObj.getInt("y");
        width = jsonObj.getInt("width");
        height = jsonObj.getInt("height");
        originPath = jsonObj.getString("originPath");
        System.out.println("in constructor" + originPath);
    }


    public Integer getResultX () {return this.resultX;}
    public Integer getResultY () {return this.resultY;}
    public Integer getX () { return this.x;}
    public Integer getY () { return this.y;}
    public Integer getWidth () {return this.width;}
    public Integer getHeight () {return this.height;}
    public String  getOriginPath () {return  this.originPath;}

}
