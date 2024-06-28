package com.codingbot.shop.core.server

object StaticServerUrlPath {
    val imgOnlinePath_Prefix = "https://afsfroagcvtulsttshvc.supabase.co/storage/v1/object/public/hospitalstatic"
    val subpath_images_path = "/staticdata/images/"
    val surgeryImgPath = imgOnlinePath_Prefix + subpath_images_path + "surgery"
    val hospitalImgPath = imgOnlinePath_Prefix + subpath_images_path + "hospitalimg"
    val eventsImgPath = imgOnlinePath_Prefix + subpath_images_path + "events"
    val doctorsImgPath = imgOnlinePath_Prefix + subpath_images_path + "doctors"

    fun getPrintResUrlsForDebug() {
        val filteringDebugText = "getPrintResUrlsForDebug-"
        println("$filteringDebugText[surgeryImgMaps]")
        surgeryImgMaps.entries.forEach {
            println("${it.value}")
        }

        println("$filteringDebugText[hospitalImgUrlMaps]")
        hospitalImgUrlMaps.entries.forEach {
            println("${it.value}")
        }

        println("$filteringDebugText[eventsImgUrlMaps]")
        eventsImgUrlMaps.entries.forEach {
            println("${it.value}")
        }

        println("$filteringDebugText[doctorsImgUrlMaps]")
        doctorsImgUrlMaps.entries.forEach {
            println("${it.value}")
        }

    }
    val surgeryImgMaps = with(surgeryImgPath) {
        mapOf(
            "surgery_acne" to "${this}/surgery_acne.png",
            "surgery_body" to "${this}/surgery_body.png",
            "surgery_botox" to "${this}/surgery_botox.png",
            "surgery_lifting" to "${this}/surgery_lifting.png",
            "surgery_pigmentation" to "${this}/surgery_pigmentation.png",
            "surgery_pore" to "${this}/surgery_pore.png",
            "surgery_skinbooster" to "${this}/surgery_skinbooster.png",
        )
    }


    val hospitalImgUrlMaps = with(hospitalImgPath) {
        mapOf(
            "reone1" to "${this}/hospital_reone.png",
            "reone2" to "${this}/hospital_reone2.png",
            "reone3" to "${this}/hospital_reone3.png",
            "reone4" to "${this}/hospital_reone4.png",
            "chungdam_ace" to "${this}/hospital_chungdamace.png",
            "wanna" to "${this}/hospital_wanna_plastic_surgery.png",
            "youjins" to "${this}/hospital2_youjins.png",
            "hospital1" to "${this}/hospital1.png",
            "brillyn" to "${this}/hospital3_brillyn.png",
            "boss" to "${this}/hospital4_boss.png",
            "vline" to "${this}/hospital5_vline.png",
        )
    }
    val eventsImgUrlMaps = with(eventsImgPath) {
        mapOf(
            "event_1" to "${this}/event_1.png",
            "event_2" to "${this}/event_2.png",
            "event_3" to "${this}/event_3.png",
            "event_4" to "${this}/event_4.png",
            "event_5" to "${this}/event_5.png",
            "event_6" to "${this}/event_6.png",
            "event_7" to "${this}/event_7.png",
            "event_8" to "${this}/event_8.png",
            "event_9" to "${this}/event_9.png",
            "event_10" to "${this}/event_10.png",
            "event_11" to "${this}/event_11.png",
        )
    }


    val doctorsImgUrlMaps = with(doctorsImgPath) {
        mapOf(
            "doctor_reone1" to "${this}/doctor_reone1.png",
            "doctor_reone2" to "${this}/doctor_reone2.png",
            "doctor_reone3" to "${this}/doctor_reone3.png",
            "doctor_wanna1" to "${this}/doctor_wanna1.png",

        )
    }

}