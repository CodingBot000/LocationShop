package com.codingbot.shop.core.server

import com.codingbot.shop.R
import com.codingbot.shop.domain.model.HomeBannerData

object InitValue {
    fun getSurgeryList(): MutableList<HomeBannerData> {
        val homeBannerData = mutableListOf<HomeBannerData>()
        SURGER_RES_LIST.entries.forEachIndexed { index, surgeryRes ->
            homeBannerData.add(
                HomeBannerData(
                    id = index,
                    resId = surgeryRes.value,
                    name = surgeryRes.name,
                    desc = ""
                )
            )
        }
        return homeBannerData
    }

    enum class SURGER_RES_LIST(val value: Int) {
        ACNE(R.drawable.surgery_acne),
        BODY(R.drawable.surgery_body),
        BOTOX(R.drawable.surgery_botox),
        LIFTING(R.drawable.surgery_lifting),
        PIGMENTATION(R.drawable.surgery_pigmentation),
        PORE(R.drawable.surgery_pore),
        SKINBOOSTER(R.drawable.surgery_skinbooster),
    }

    enum class RECOMMEND_MENU(val value: String) {
        EVENT("Event"),
        REVIEW("Review"),
        Hospital("Hospital"),
    }
    val BANNER_SLIDERS = arrayOf(
//        1,2,3,1001,1002,1003,1004,1005
        R.drawable.surgery_acne,
        R.drawable.surgery_body,
        R.drawable.surgery_botox,
        R.drawable.surgery_lifting,
        R.drawable.surgery_pigmentation,
        R.drawable.surgery_pore,
        R.drawable.surgery_skinbooster,
    )


    val MENU_MAIN_CATEGORIES =
        arrayListOf(
            "Surgical procedure",
            "Cosmetic procedure",
            "Location",
            "Favorite",
            "Event",
            "About Us"
        )

    val MENU_SUB_SURGERY =
        arrayListOf(
            "Eyes",
            "Nose",
            "bimaxillary operation",
            "liposuction",
            "Hair transplantation",
        )

    val MENU_SUB_COSMETICS =
        arrayListOf(
            "Ulthera",
            "Thermage",
            "InMode",
            "Shrink",
            "Botox",
            "Filler",
            "Skinbooster",
            "tune face",
            "Thermage",

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