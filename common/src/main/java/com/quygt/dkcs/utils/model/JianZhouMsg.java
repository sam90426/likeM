package com.quygt.dkcs.utils.model;

public class JianZhouMsg {
    private boolean result;
    private String errorMessage;

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public JianZhouMsg(boolean _result, String _errorMessage) {
        this.result = _result;
        this.errorMessage = _errorMessage;
    }
}
