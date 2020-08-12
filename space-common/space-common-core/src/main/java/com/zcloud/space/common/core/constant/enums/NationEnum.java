
package com.zcloud.space.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description
 * @Author
 * @Date
 */
@Getter
@AllArgsConstructor
public enum NationEnum {
    HZ("01", "汉族"),
    MGZ("02", "蒙古族"),
    HUIZ("03", "回族"),
    ZZ("04", "藏族"),
    WWEZ("05", "维吾尔族"),
    MZ("06", "苗族"),
    YZ("07", "彝族"),
    ZHUANGZ("08", "壮族"),
    BYZ("09", "布依族"),
    CXZ("10", "朝鲜族"),
    MANZ("11", "满族"),
    DZ("12", "侗族"),
    YAOZ("13", "瑶族"),
    BZ("14", "白族"),
    TJZ("15", "土家族"),
    HNZ("16", "哈尼族"),
    HSKZ("17", "哈萨克族"),
    DAIZ("18", "傣族"),
    LZ("19", "黎族"),
    LLZ("20", "傈僳族"),
    WZ("21", "佤族"),
    SZ("22", "畲族"),
    GSZ("23", "高山族"),
    LKZ("24", "拉祜族"),
    SHUIZ("25", "水族"),
    DSZ("26", "东乡族"),
    NAZ("27", "纳西族"),
    JPZ("28", "景颇族"),
    KEKZ("29", "柯尔克孜"),
    TUZ("30", "土族"),
    DHEZ("31", "达斡尔族"),
    YLZ("32", "仫佬族"),
    QZ("33", "羌族"),
    BULZ("34", "布朗族"),
    SLZ("35", "撒拉族"),
    MLZ("36", "毛南族"),
    QLZ("37", "仡佬族"),
    QBZ("38", "锡伯族"),
    ACZ("39", "阿昌族"),
    PMZ("40", "普米族"),
    TJKZ("41", "塔吉克族"),
    NZ("42", "怒族"),
    WZBK("43", "乌孜别克"),
    ELSZ("44", "俄罗斯族"),
    EWKZ("45", "鄂温克族"),
    BLZ("46", "崩龙族"),
    BAZ("47", "保安族"),
    YGZ("48", "裕固族"),
    JZ("49", "京族"),
    TTEZ("50", "塔塔尔族"),
    DLZ("51", "独龙族"),
    ELCZ("52", "鄂伦春族"),
    HZZ("53", "赫哲族"),
    MBZ("54", "门巴族"),
    LBZ("55", "珞巴族"),
    JNZ("56", "基诺族"),
    OTHER("99", "其他");

    private final String code;
    private final String title;

    public static String getTitleByCode(String code) {
        for (NationEnum m : NationEnum.values()) {
            if (m.getCode().equals(code)) {
                return m.getTitle();
            }
        }
        return NationEnum.HZ.getTitle();
    }

}
