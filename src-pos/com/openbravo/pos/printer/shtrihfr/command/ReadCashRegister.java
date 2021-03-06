/*
 * ReadCashRegister.java
 *
 * Created on 2 April 2008, 20:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.openbravo.pos.printer.shtrihfr.command;

/**
 *
 * @author V.Kravtsov
 */

import com.openbravo.pos.printer.shtrihfr.fiscalprinter.CommandInputStream;
import com.openbravo.pos.printer.shtrihfr.fiscalprinter.CommandOutputStream;
import com.openbravo.pos.printer.shtrihfr.util.MethodParameter;

/****************************************************************************
 
    Get Cash Totalizer Value

    Command:	1AH. Length: 6 bytes. 
    ·	Operator password (4 bytes)
    ·	Cash totalizer number (1 byte) 0…255
    Answer:		1AH. Length: 9 bytes.
    ·	Result Code (1 byte)
    ·	Operator index number (1 byte) 1…30
    ·	Cash totalizer value (6 bytes)

****************************************************************************/
public final class ReadCashRegister extends PrinterCommand {
    // in
    private final int password;
    private final int totalizerNumber;
    // out 
    private int operator = 0;
    private long totalizerValue = 0;

    /**
     * Creates a new instance of ReadCashRegister
     */
    public ReadCashRegister(
            int password, 
            int totalizerNumber) {
        MethodParameter.checkRange(totalizerNumber, 0, 0xFF, "totalizer number");

        this.password = password;
        this.totalizerNumber = totalizerNumber;
    }

    public final int getCode() {
        return 0x1A;
    }

    public final String getText() {
        return "Get cash totalizer value";
    }

    public final void encode(CommandOutputStream out) throws Exception {
        out.writeInt(password);
        out.writeByte(totalizerNumber);
    }

    public final void decode(CommandInputStream in) throws Exception {
        operator = in.readByte();
        totalizerValue = in.readLong(6);
    }

    public int getOperator() {
        return operator;
    }

    public long getTotalizerValue() {
        return totalizerValue;
    }
}
