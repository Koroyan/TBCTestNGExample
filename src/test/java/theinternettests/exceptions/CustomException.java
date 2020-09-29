package theinternettests.exceptions;

public class CustomException extends Exception {
    public CustomException(int index,String text){
        super("in"+index+"index "+ text+ "last index is not zero");
    }

}
