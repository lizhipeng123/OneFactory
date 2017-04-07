package com.daoran.newfactory.onefactory.util.Http;

import org.apache.http.StatusLine;

public class NetException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -4395338594090466251L;

    private StatusLine statusLine;

    public NetException(StatusLine statusLine) {
        super(statusLine.getStatusCode() + ":" + statusLine.getReasonPhrase());
        this.statusLine = statusLine;
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

}
