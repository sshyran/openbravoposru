/*
 * ReadEJReportDepartmentDays.java
 *
 * Created on 16 January 2009, 14:26
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
 
    Get Data Of Daily Totals Report In Daily Totals Number Range From EKLZ
 
    Command:	B7H. Length: 11 bytes.
    ·	System Administrator password (4 bytes) 30
    ·	Report type (1 byte) «0» – short, «1» – full
    ·	Department number (1 byte) 1…16
    ·	First day number (2 bytes) 0000…2100
    ·	Last day number (2 bytes) 0000…2100
 
    Answer:		B7H. Length: 18 bytes.
    ·	Result Code (1 byte)
    ·	ECR model (16 bytes) string of WIN1251 code page characters
 
    NOTE: Command execution may take up to 150 seconds.
 
****************************************************************************/
public final class ReadEJReportDepartmentDays extends PrinterCommand {
    // in 
    private final int password;
    private final byte reportType;
    private final byte department;
    private final int dayNumber1;
    private final int dayNumber2;
    // out
    private String ecrModel = new String("");

    /**
     * Creates a new instance of ReadEJReportDepartmentDays
     */
    public ReadEJReportDepartmentDays(
            int password,
            byte reportType,
            byte department,
            int dayNumber1,
            int dayNumber2) {
        this.password = password;
        this.reportType = reportType;
        this.department = department;
        this.dayNumber1 = dayNumber1;
        this.dayNumber2 = dayNumber2;
    }

    public final int getCode() {
        return 0xB7;
    }

    public final String getText() {
        return "Read electronic journal department report in dayNumber range";
    }

    public final void encode(CommandOutputStream out) throws Exception {
        out.writeInt(password);
        out.writeByte(reportType);
        out.writeByte(department);
        out.writeShort(dayNumber1);
        out.writeShort(dayNumber2);
    }

    public final void decode(CommandInputStream in) throws Exception {
        ecrModel = in.readString(in.getSize());
    }

    public String getEcrModel() {
        return ecrModel;
    }
}
