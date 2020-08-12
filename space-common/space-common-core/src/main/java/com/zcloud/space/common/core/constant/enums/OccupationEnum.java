
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
public enum OccupationEnum {
    GJGWY("11", "国家公务员"),
    ZYJSRY("13", "专业技术人员"),
    ZY("17", "工人"),
    QYGLRY("21", "农民"),
    GR("24", "干部"),
    NM("27", "教师"),
    XS("31", "工人"),
    XYJR("37", "农民"),
    ZYZYZ("51", "干部"),
    GTJYZ("54", "教师"),
    WYRY("70", "工人"),
    TXRY("80", "农民"),
    OTHER("90", "其他");

    private final String code;
    private final String title;

    public static String getTitleByCode(String code){
        for (OccupationEnum m: OccupationEnum.values()){
            if(m.getCode().equals(code)){
                return m.getTitle();
            }
        }
        return OccupationEnum.OTHER.getTitle();
    }
}
