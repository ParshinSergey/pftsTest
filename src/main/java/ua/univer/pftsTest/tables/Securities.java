package ua.univer.pftsTest.tables;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SECURITIES")
public class Securities {

    // --- Общие сведения об инструменте ---
    @XmlAttribute(name = "a")
    private String secBoard;        // Режим торгов (4 симв.)

    @XmlAttribute(name = "b")
    private String secCode;         // Код инструмента (12 симв.)

    @XmlAttribute(name = "c")
    private String secName;         // Наименование инструмента (30 симв.)

    @XmlAttribute(name = "d")
    private String remarks;         // Примечания (8 симв.)

    @XmlAttribute(name = "e")
    private String shortName;       // Краткое название (10 симв.)

    // --- Статусы ---
    @XmlAttribute(name = "f")
    private String status;          // Статус (дозволено/заборонено)

    @XmlAttribute(name = "g")
    private String tradingStatus;   // Состояние торговой сессии

    // --- Параметры рынка ---
    @XmlAttribute(name = "h")
    private String marketCode;      // Рынок (4 симв.)

    @XmlAttribute(name = "i")
    private String instrId;         // Группа инструментов (4 симв.)

    @XmlAttribute(name = "j")
    private Integer lotSize;        // Размер лота

    @XmlAttribute(name = "k")
    private BigDecimal minStep;     // Мин. шаг цены

    @XmlAttribute(name = "l")
    private BigDecimal faceValue;   // Номинал

    @XmlAttribute(name = "m")
    private String faceUnit;        // Валюта номинала (4 симв.)

    // --- Предыдущие показатели ---
    @XmlAttribute(name = "n")
    private String prevDate;     // Дата предыдущих торгов  ---- LocalDate

    @XmlAttribute(name = "o")
    private BigDecimal prevPrice;   // Последняя цена пред. дней

    @XmlAttribute(name = "p")
    private Integer decimals;       // Количество десятичных знаков

    @XmlAttribute(name = "q")
    private BigDecimal yield;       // Доходность по последней сделке

    @XmlAttribute(name = "r")
    private BigDecimal accruedInt;  // НКД (Накопленный купонный доход)

    @XmlAttribute(name = "s")
    private String primaryDist;     // Первичное размещение (YesNo)

    // --- Облигации и выпуски ---
    @XmlAttribute(name = "t")
    private String matDate;       // Дата погашения   ---- LocalDate

    @XmlAttribute(name = "u")
    private BigDecimal couponValue;  // Величина купона

    @XmlAttribute(name = "v")
    private Integer couponPeriod;    // Длительность купона

    @XmlAttribute(name = "w")
    private String nextCoupon;    // Дата окончания купона  ---- LocalDate

    @XmlAttribute(name = "x")
    private Long issueSize;          // Объем выпуска

    // --- Средневзвешенные цены и досрочный выкуп ---
    @XmlAttribute(name = "y")
    private BigDecimal prevWaPrice;        // Пред. средневзвешенная цена

    @XmlAttribute(name = "z")
    private BigDecimal yieldAtPrevWaPrice; // Доходность по пред. средневзв. цене

    @XmlAttribute(name = "ba")
    private String currencyId;             // Валюта расчетов

    @XmlAttribute(name = "bb")
    private BigDecimal buyBackPrice;       // Цена досрочного выкупа

    @XmlAttribute(name = "bc")
    private String buyBackDate;         // Дата досрочного выкупа    ---LocalDate

    @XmlAttribute(name = "bd")
    private String agentId;                // Агент

    @XmlAttribute(name = "be")
    private String quoteBasis;             // Тип цены

    @XmlAttribute(name = "bf")
    private String isin;                   // ISIN код

    @XmlAttribute(name = "bg")
    private String latName;                // Англ. наименование

    @XmlAttribute(name = "bh")
    private String regNumber;              // Регистрационный номер

    // --- Текущие котировки (Стакан) ---
    @XmlAttribute(name = "bi")
    private BigDecimal prevLastBid;        // Попрос пред. дня

    @XmlAttribute(name = "bj")
    private BigDecimal prevLastOffer;      // Предложение пред. дня

    @XmlAttribute(name = "bk")
    private String secType;                // Тип ценной бумаги

    @XmlAttribute(name = "bl")
    private String activationDate;      // Дата активации    ---- LocalDate

    @XmlAttribute(name = "bm")
    private BigDecimal bid;                // Лучший спрос

    @XmlAttribute(name = "bn")
    private Integer bidDepth;              // Лотов на покупку по лучшей цене

    @XmlAttribute(name = "bo")
    private Integer bidDepthT;             // Совокупный спрос

    @XmlAttribute(name = "bp")
    private Integer numBids;               // Количество заявок на покупку

    @XmlAttribute(name = "bq")
    private BigDecimal offer;              // Лучшее предложение

    @XmlAttribute(name = "br")
    private Integer offerDepth;            // Лотов на продажу по лучшей цене

    @XmlAttribute(name = "bs")
    private Integer offerDepthT;           // Совокупное предложение

    @XmlAttribute(name = "bt")
    private Integer numOffers;             // Количество заявок на продажу

    // --- Данные по последней сделке ---
    @XmlAttribute(name = "bu")
    private BigDecimal open;               // Цена открытия (первая сделка)

    @XmlAttribute(name = "bv")
    private BigDecimal high;               // Максимум

    @XmlAttribute(name = "bw")
    private BigDecimal low;                // Минимум

    @XmlAttribute(name = "bx")
    private BigDecimal last;               // Последняя цена

    @XmlAttribute(name = "by")
    private BigDecimal change;             // Изменение к закрытию пред. дня

    @XmlAttribute(name = "bz")
    private Integer qty;                   // Лотов в последней сделке

    @XmlAttribute(name = "ca")
    private String time;                // Время последней сделки   ----- LocalDate

    // --- Объемы и итоги за сегодня ---
    @XmlAttribute(name = "cb")
    private Long volToday;                 // Количество бумаг за сегодня

    @XmlAttribute(name = "cc")
    private Long valToday;                 // Объем в валюте за сегодня

    @XmlAttribute(name = "cd")
    private BigDecimal value;              // Объем в последней сделке

    @XmlAttribute(name = "ce")
    private BigDecimal waPrice;            // Средневзвешенная цена

    @XmlAttribute(name = "cf")
    private BigDecimal highBid;            // Лучший спрос за сессию

    @XmlAttribute(name = "cg")
    private BigDecimal lowOffer;           // Лучшее предложение за сессию

    @XmlAttribute(name = "ch")
    private Integer numTrades;             // Количество сделок за сегодня

    // --- Дополнительные расчетные показатели ---
    @XmlAttribute(name = "ci")
    private BigDecimal yieldAtWaPrice;        // Доходность по средневзвешенной цене

    @XmlAttribute(name = "cj")
    private BigDecimal priceMinusPrevWaPrice; // Отклонение цены от предыдущей средневзвешенной

    @XmlAttribute(name = "ck")
    private BigDecimal closePrice;            // Цена закрытия

    @XmlAttribute(name = "cl")
    private BigDecimal closeYield;            // Доходность при закрытии

    // --- Итоговые котировки сессии ---
    @XmlAttribute(name = "cm")
    private BigDecimal lastBid;               // Лучший спрос на момент завершения торгов

    @XmlAttribute(name = "cn")
    private BigDecimal lastOffer;             // Лучшее предложение на момент завершения торгов

    @XmlAttribute(name = "co")
    private String lastSettleCode;            // Код расчетов по последней сделке

    // --- Контроль цен и предупреждения ---
    @XmlAttribute(name = "cp")
    private BigDecimal warnPrice;             // Предупредительная цена

    @XmlAttribute(name = "cq")
    private BigDecimal warnPricePct;          // Предупредительная цена в процентах

    @XmlAttribute(name = "cr")
    private String warnPriceType;             // Тип предупредительной цены

    // --- Маркет-прайс и лимиты ---
    @XmlAttribute(name = "cs")
    private BigDecimal marketPrice;           // Контрольная цена (direct price)

    @XmlAttribute(name = "ct")
    private BigDecimal marketPriceToday;      // Контрольная цена на сегодня

    @XmlAttribute(name = "cu")
    private BigDecimal duration;              // Длительность (дюрация)

    // --- Коды расчетов и периоды ---
    @XmlAttribute(name = "cv")
    private String settleCode;                // Код стандартных расчетов

    @XmlAttribute(name = "cw")
    private BigDecimal openPeriodPrice;       // Цена передторгового периода

    @XmlAttribute(name = "cx")
    private BigDecimal counterPrice;          // Встречная цена

    @XmlAttribute(name = "cy")
    private BigDecimal minCurrLast;           // Минимальная текущая/последняя цена
}



