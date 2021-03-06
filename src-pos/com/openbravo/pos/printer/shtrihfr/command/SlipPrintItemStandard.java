/*
 * SlipPrintItemStandard.java
 *
 * Created on January 15 2009, 15:12
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
import com.openbravo.pos.printer.shtrihfr.fiscalprinter.PriceItem;

/****************************************************************************

    Standard Transaction On Slip
 
    Command:	73H. Length: 61 bytes.
    ·	Operator password (4 bytes)
    ·	Slip line number with the first line of transaction block (1 byte)
    ·	Quantity (5 bytes)
    ·	Unit Price (5 bytes)
    ·	Department (1 byte) 0…16
    ·	Tax 1 (1 byte) «0» – no tax, «1»…«4» – tax ID
    ·	Tax 2 (1 byte) «0» – no tax, «1»…«4» – tax ID
    ·	Tax 3 (1 byte) «0» – no tax, «1»…«4» – tax ID
    ·	Tax 4 (1 byte) «0» – no tax, «1»…«4» – tax ID
    ·	Text (40 bytes)
 
    Answer:		73H. Length: 3 bytes.
    ·	Result Code (1 byte)
    ·	Operator index number (1 byte) 1…30
    
****************************************************************************/
public final class SlipPrintItemStandard extends PrinterCommand {
    // in params

    private final int password;
    private final int line;
    private final PriceItem item;
    // out params
    private int operator;

    /**
     * Creates a new instance of SlipPrintItemStandard
     */
    public SlipPrintItemStandard(
            int password, 
            int line, 
            PriceItem item) {
        this.password = password;
        this.line = line;
        this.item = item;
    }

    public final int getCode() {
        return 0x73;
    }

    public final String getText() {
        return "Standard transaction on slip";
    }

    public final void encode(CommandOutputStream out) throws Exception {
        out.writeInt(password);
        out.writeByte(line);
        // item
        out.writeLong(item.quantity, 5);
        out.writeLong(item.price, 5);
        out.writeByte(item.department);
        out.writeByte(item.tax1);
        out.writeByte(item.tax2);
        out.writeByte(item.tax3);
        out.writeByte(item.tax4);
        out.writeString(item.text, 40);
    }

    public final void decode(CommandInputStream in) throws Exception {
        operator = in.readByte();
    }

    public int getOperator() {
        return operator;
    }
}
