/*
 * SlipCloseStandard.java
 *
 * Created on January 15 2009, 18:03
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.openbravo.pos.printer.shtrihfr.command;

/**
 *
 * @author V.Kravtsov
 */

import com.openbravo.pos.printer.shtrihfr.fiscalprinter.CloseRecParams;
import com.openbravo.pos.printer.shtrihfr.fiscalprinter.CommandInputStream;
import com.openbravo.pos.printer.shtrihfr.fiscalprinter.CommandOutputStream;

/****************************************************************************

    Close Standard Fiscal Slip
 
    Command:	77H. Length: 72 bytes.
    ·	Operator password (4 bytes)
    ·	Slip line number with the first line of Close Fiscal Slip block (1 byte)
    ·	Cash Payment value (5 bytes)
    ·	Payment Type 2 value (5 bytes)
    ·	Payment Type 3 value (5 bytes)
    ·	Payment Type 4 value (5 bytes)
    ·	Receipt Discount Value 0 to 99,99 % (2 bytes) 0000…9999
    ·	Tax 1 (1 byte) «0» – no tax, «1»…«4» – tax ID
    ·	Tax 2 (1 byte) «0» – no tax, «1»…«4» – tax ID
    ·	Tax 3 (1 byte) «0» – no tax, «1»…«4» – tax ID
    ·	Tax 4 (1 byte) «0» – no tax, «1»…«4» – tax ID
    ·	Text (40 bytes)
 
    Answer:		77H. Length: 8 bytes.
    ·	Result Code (1 byte)
    ·	Operator index number (1 byte) 1…30
    ·	Change value (5 bytes) 0000000000…9999999999

****************************************************************************/
public final class SlipCloseStandard extends PrinterCommand {

    // in
    private final int password;
    private final int line;
    private final CloseRecParams params;
    // out
    private int operator = 0;
    private long change = 0;

    /**
     * Creates a new instance of SlipCloseStandard
     */
    public SlipCloseStandard(
            int password, 
            int line, 
            CloseRecParams params) {
        this.password = password;
        this.line = line;
        this.params = params;
    }

    public final int getCode() {
        return 0x77;
    }

    public final String getText() {
        return "Close standard fiscal slip";
    }

    public final void encode(CommandOutputStream out) throws Exception {
        out.writeInt(password);
        out.writeByte(line);
        out.writeLong(params.sum1, 5);
        out.writeLong(params.sum2, 5);
        out.writeLong(params.sum3, 5);
        out.writeLong(params.sum4, 5);
        out.writeShort(params.discount);
        out.writeByte(params.tax1);
        out.writeByte(params.tax2);
        out.writeByte(params.tax3);
        out.writeByte(params.tax4);
        out.writeString(params.text, 40);
    }

    public final void decode(CommandInputStream in) throws Exception {
        operator = in.readByte();
        change = in.readLong(5);
    }

    public int getOperator() {
        return operator;
    }

    public long getChange() {
        return change;
    }
}
