/*
 * PrinterException.java
 *
 * Created on March 13 2008, 13:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.openbravo.pos.printer.shtrihfr.fiscalprinter;

/**
 *
 * @author V.Kravtsov
 */

public class PrinterException extends Exception
{
    private final int errorCode;
    
    public PrinterException(int errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
    }
    
    public PrinterException(int errorCode)
    {
        super(PrinterError.getFullText(errorCode));
        this.errorCode = errorCode;
    }
    
    public int getErrorCode()
    {
        return errorCode;
    }
}
