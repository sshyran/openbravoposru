/*
 * StopFullReport.java
 *
 * Created on January 15 2009, 14:28
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

    Interrupt Full Report
 
    Command:	68H. Length: 5 bytes.
    ·	Tax Officer password (4 bytes)
 
    Answer:		68H. Length: 2 bytes.
    ·	Result Code (1 byte)
    
****************************************************************************/
public final class StopFullReport extends PrinterCommand {
    // in params

    private final int password;

    /**
     * Creates a new instance of StopFullReport
     */
    public StopFullReport(int password) {
        this.password = password;
    }

    public final int getCode() {
        return 0x68;
    }

    public final String getText() {
        return "Interrupt full report";
    }

    public final void encode(CommandOutputStream out) throws Exception {
        out.writeInt(password);
    }

    public final void decode(CommandInputStream in) throws Exception {
    }
}
