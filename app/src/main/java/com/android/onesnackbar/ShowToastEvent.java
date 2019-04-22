package com.android.onesnackbar;

public class ShowToastEvent {
    private String message;

    public ShowToastEvent(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
