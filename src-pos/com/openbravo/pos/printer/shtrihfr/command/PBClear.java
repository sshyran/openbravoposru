/*
 * PBClear.java
 *
 * Created on January 16 2009, 15:23
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

/****************************************************************************
 
    Clear print buffer
 
    Command: 	CAH. Message length: 5 bytes.
    ·	Operator password (4 bytes)
 
    Answer:		CAH. Message length: 2 bytes
    ·	Result code (1 byte)
 
****************************************************************************/
public final class PBClear extends PrinterCommand {
    // in 
    private final int password;

    /**
     * Creates a new instance of PBClear
     */
    public PBClear(int password) {
        this.password = password;
    }

    public final int getCode() {
        return 0xCA;
    }

    public final String getText() {
        return "Clear print buffer";
    }

    public final void encode(CommandOutputStream out) throws Exception {
        out.writeInt(password);
    }

    public final void decode(CommandInputStream in) throws Exception {
    }
}
