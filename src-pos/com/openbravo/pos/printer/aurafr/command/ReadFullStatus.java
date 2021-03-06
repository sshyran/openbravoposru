/*
 * ReadFullStatus.java
 */
package com.openbravo.pos.printer.aurafr.command;

import com.openbravo.pos.printer.aurafr.fiscalprinter.PrinterStatus;
import com.openbravo.pos.util.ByteArrayUtils;

/**
 *
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class ReadFullStatus extends PrinterCommand {

    /*
     * Запрос состояния ККМ
     *
     * Команда: 0x3F
     *
     * Ответ: 0x44 <Кассир(1)> <Номер_в_зале(1)> <Дата_YMD(3)> <Время_HMS(3)>
     * <Флаги(1)> <Заводской_номер(4)> <Модель(1)> <Версия_ККМ(2)>
     * <Режим_работы(1)> <Номер_чека(2)> <Номер_смены(2)> <Состояние_чека(1)>
     * <Сумма_чека(5)> <Десятичная_точка(1)> <Порт(1)>
     * 
     * <Кассир(1)> 00..30, формат BCD, (значения 01..30 можно получить в режимах
     * 1..4 при Подрежим ≠ 1, в остальных режимах поле смысла не имеет.
     *     
     * <Номер_в_зале(1)> 01..99, формат BCD.
     *
     * <Дата_YMD(3)> 00..99, 01..12, 01..31 (98 - 1998, 99 - 1999, 00 - 2000,
     * ..., 89 - 2089, 90..97 - запрещенные значения) – показания внутренних
     * часов ККМ.
     *
     * <Время_HMS(3)> 00..23, 00..59, 00..59 – показания внутренних часов ККМ.
     *     
     * <Флаги(1)> Битовое поле (назначение бит): 
     * 0 – ККМ фискализирована (0 - нет, 1 - да);
     * 1 – смена открыта (0 - нет, 1 - да);
     * 2 – денежный ящик открыт (0 - да, 1 - нет); 
     * 3 – весовой датчик ЧЛ (0-нет бумаги, 1-есть бумага); 
     * 4 – бит не используется; 
     * 5 – состояние датчика крышки (0 – крышка закрыта, 1 – крышка открыта); 
     * 6 – бит не используется и должен содержать 0; 
     * 7 – равен 1, если напряжение на батарейке меньше допустимого.
     * 
     * <Заводской_номер(4)> 00000000 .. 99999999 (FFh FFh FFh FFh -
     * демонстрационная версия (NFR)), формат BCD.
     *
     * <Модель(1)> Аналогично параметру «Модель устройства».
     * 
     * <Версия_ККМ(2)> 2 ASCII-символа, между которыми надо вставить символ
     * «точка». Например «24» соответствует 2.4.
     * 
     * <Режим_работы(1)> см. команду Запрос кода состояния ККМ
     *
     * <Номер_чека(2)> 0000..9999 (нумерация сквозная), формат BCD. Содержит
     * «номер последнего закрытого чека + 1».
     *
     * <Номер_смены(2)> 0000 .. 9999 (нумерация сквозная). 
     * Внимание! Номер смены – номер последней закрытой смены, а не текущей. 
     * Примечание: Всегда до фискализации ККМ и до снятия первого суточного
     * отчета с гашением после фискализации ККМ номер последней закрытой смены
     * равен 0000.
     *     
     * <Состояние_чека(1)> Битовое поле. Назначение бит: биты 0 .. 3 - Состояние
     * чека:
     * 0-1 бит: 0 – чек закрыт, 1 – открыт чек регистрации, 2- открыт чек
     * возврата, 3 – открыт чек аннулирования.
     * 2 бит: 0 – чек продажи, 1 – чек покупки (тип чека – биты 0- 1).
     * 3 бит: 0 – чек формируется сразу, 1 – формируется отложенный документ.
     *     
     * <Сумма_чека(5)> 0000000000 .. 9999999999 мде. - сумма текущего чека
     * (имеет смысл только в режиме регистрации), формат BCD.
     *
     * <Десятичная_точка(1)> 0 .. 3 - положение десятичной точки во всех
     * денежных величинах (кол-во разрядов справа от десятичной точки).
     *
     * <Порт(1)> Номер порта ККМ, к которому подключен ПК. Формат –
     * двоично-десятичное число из диапазона: 1, 2, 3.
     * 
     */
    
    private PrinterStatus status = new PrinterStatus();    
    
    public ReadFullStatus() {
    }

    public final int getCode() {
        return 0x3F;
    }

    public final String getText() {
        return "Get status";
    }

    public byte[] getMessageData() {
        return null;
    }

    public int getMessageDataSize() {
        return 0;
    }

    public final boolean isAnswer() {
        return true;
    }

    @Override
    public void readAnswerData(byte[] data) {
//        System.out.println("Packet(" + data.length + "):" + ByteArrayUtils.getHexString(data));
    }
}
