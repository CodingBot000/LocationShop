package com.codingbot.shop.core.server

import com.codingbot.shop.R
import com.codingbot.shop.domain.model.HomeBannerData
import com.codingbot.shop.ui.screens.menu.SectionSubData

object InitValue {
    enum class SURGER_RES_LIST(val value: String) {
        ACNE(StaticServerUrlPath.surgeryImgMaps["surgery_acne"]!!),
        BODY(StaticServerUrlPath.surgeryImgMaps["surgery_body"]!!),
        BOTOX(StaticServerUrlPath.surgeryImgMaps["surgery_botox"]!!),
        LIFTING(StaticServerUrlPath.surgeryImgMaps["surgery_lifting"]!!),
        PIGMENTATION(StaticServerUrlPath.surgeryImgMaps["surgery_pigmentation"]!!),
        PORE(StaticServerUrlPath.surgeryImgMaps["surgery_pore"]!!),
        SKINBOOSTER(StaticServerUrlPath.surgeryImgMaps["surgery_skinbooster"]!!),
    }
    val MENU_MAIN_CATEGORIES =
        arrayListOf(
            MAIN_MENU_NAME.SURGICAL_PROCEDURE,
            MAIN_MENU_NAME.COSMETIC_PROCEDURE,
            MAIN_MENU_NAME.LOCATION,
            MAIN_MENU_NAME.FAVORITE,
            MAIN_MENU_NAME.EVENT,
            MAIN_MENU_NAME.ABOUT_US
        )

    val MENU_SUB_SURGERY =
        arrayListOf(
            SectionSubData(id = 1000, subText = "Eyes"),
            SectionSubData(id = 1001, subText = "Nose"),
            SectionSubData(id = 1002, subText = "bimaxillary operation"),
            SectionSubData(id = 1003, subText = "liposuction"),
            SectionSubData(id = 1004, subText = "Hair transplantation")
        )

    val MENU_SUB_COSMETICS =
        arrayListOf(
            SectionSubData(id = 0, subText = "Ulthera"),
            SectionSubData(id = 1, subText = "Thermage"),
            SectionSubData(id = 2, subText = "InMode"),
            SectionSubData(id = 3, subText = "Shrink"),
            SectionSubData(id = 4, subText = "Botox"),
            SectionSubData(id = 5, subText = "Filler"),
            SectionSubData(id = 6, subText = "Skinbooster"),
            SectionSubData(id = 7, subText = "tune face"),

        )

    val MENU_SUB_LOCATIONS =
        arrayListOf(
            LocationsData.LocationNameConst.APGUJEONG,
            LocationsData.LocationNameConst.MYUNGDONG,
            LocationsData.LocationNameConst.GANGNAM,
            LocationsData.LocationNameConst.CHUNGDAM,
            LocationsData.LocationNameConst.SONGDO,
            LocationsData.LocationNameConst.HONGDAE,
        )

    enum class LocationNames(val value: String) {
        APGUJEONG(LocationsData.LocationNameConst.APGUJEONG),
        MYUNGDONG(LocationsData.LocationNameConst.MYUNGDONG),
        GANGNAM(LocationsData.LocationNameConst.GANGNAM),
        CHUNGDAM(LocationsData.LocationNameConst.CHUNGDAM),
        SONGDO(LocationsData.LocationNameConst.SONGDO),
        HONGDAE(LocationsData.LocationNameConst.HONGDAE),
    }
    object LocationsData {
        object LocationNameConst {
            val APGUJEONG = "APGUJEONG"
            val MYUNGDONG = "MYUNGDONG"
            val GANGNAM = "GANGNAM"
            val CHUNGDAM = "CHUNGDAM"
            val SONGDO = "SONGDO"
            val HONGDAE = "HONGDAE"
        }

        fun getHospitalList(location: LocationNames): List<String> {
            return when(location) {
                LocationNames.APGUJEONG ->
                    arrayListOf(
                        "리원피부과의원",
                        "청담에이스의원",
                        "워나성형외과의원",
                        "레아주서울피부과",
                        "웰스피부과 압구정본점",
                        "오늘안피부과의원",
                        "드림피부과의원",
                        "더힐피부과의원",
                        "베리굿피부과의원",
                        "더3.0피부과의원",
                    "압구정 리더스피부과의원")
                LocationNames.MYUNGDONG ->
                    arrayListOf(
                        "아비쥬의원",
                        "제이디의원",
                        "리엔장의원",
                        "씨앤피차앤박피부과의원",
                        "아미스킨의원",
                        "세가지소원의원명동점",
                        "더프리티영의원",
                        "톡스앤필의원명동점",
                        )
                LocationNames.GANGNAM ->
                    arrayListOf(
                        "디알피부과의원",
                        "세알피부과",
                        "셀린피부과의원",
                        "강남더의원",
                        "오가나셀 피부과의원",
                        "청담연세피부과",
                        "강남청담미의원",
                        "서울에이치피부과의원",
                        "예젤의원"
                    )
                LocationNames.CHUNGDAM ->
                    arrayListOf(
                        "청담피부과의원",
                        "CU클린업피부과의원 청담점",
                        "청담아티젠클리닉",
                        "청담힐의원",
                        "청담연세피부과",
                        "스킨다피부과의원청담점",
                        "디오디피부과의원 청담",
                        "오가나셀 피부과의원",
                    )

                LocationNames.SONGDO ->
                    arrayListOf(
                        "신사에그의원",
                        "청담은피부과의원",
                        "신사인피부과의원",
                        "피에이치디피부과의원",
                        "송도연세피부과의원",
                        "휴먼피부과의원",
                        "리더스피부과 송도점",)
                LocationNames.HONGDAE ->
                    arrayListOf(
                        "홍대예쁨주의쁨의원",
                        "홍대 고운세상피부과",
                        "유앤미의원 홍대점",
                        "닥터쁘띠의원 홍대점",
                        "닥터디자이너의원 홍대",
                        "마인드피부과의원",
                        "에스준의원 홍대",)
            }
        }
    }

}

object MAIN_MENU_NAME {
    const val SURGICAL_PROCEDURE = "Surgical procedure"
    const val COSMETIC_PROCEDURE = "Cosmetic procedure"
    const val LOCATION = "Location"
    const val FAVORITE = "Favorite"
    const val EVENT = "Event"
    const val ABOUT_US = "About Us"
}