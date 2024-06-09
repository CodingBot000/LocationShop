package com.codingbot.shop.core.common

import com.codingbot.shop.R



fun imageLocalMapperTmpHospital(imageName: String): Int {

    return when (imageName) {
        "hospital_wanna_plastic_surgery.png" -> R.drawable.hospital_wanna_plastic_surgery
        "hospital_chungdamace.png" -> R.drawable.hospital_chungdam_ace
        "hospital_reone.png" -> R.drawable.hospital_reone
        "hospital_reone2.png" -> R.drawable.hospital_reone2
        "hospital_reone3.png" -> R.drawable.hospital_reone3
        "hospital_reone4.png" -> R.drawable.hospital_reone4
        "hospital1.png" -> R.drawable.hospital1
        "hospital2_youjins.png" -> R.drawable.hospital2_youjins
        "hospital3_brillyn.png" -> R.drawable.hospital3_brillyn
        "hospital4_boss.png" -> R.drawable.hospital4_boss
        "hospital5_vline.png" -> R.drawable.hospital5_vline
        else -> R.drawable.ic_launcher_background
    }
}

fun imageLocalMapperTmpEvent(imageName: String): Int {

    return when (imageName) {
        "event_1.png" -> R.drawable.event_1
        "event_2.png" -> R.drawable.event_2
        "event_3.png" -> R.drawable.event_3
        "event_4.png" -> R.drawable.event_4
        "event_5.png" -> R.drawable.event_5
        "event_6.png" -> R.drawable.event_6
        "event_7.png" -> R.drawable.event_7
        "event_8.png" -> R.drawable.event_8
        "event_9.png" -> R.drawable.event_9
        "event_10.png" -> R.drawable.event_10
        "event_11.png" -> R.drawable.event_11
        else -> R.drawable.ic_launcher_background
    }
}
fun imageLocalMapperTmpReview(imageName: String): Int {

    return when (imageName) {
        "review_1.png" -> R.drawable.review_1
        "review_2.png" -> R.drawable.review_2
        "review_3.png" -> R.drawable.review_3
        else -> R.drawable.ic_launcher_background
    }
}

fun imageLocalMapperTmpDoctors(imageName: String): Int {

    return when (imageName) {
        "doctor_reone1.png" -> R.drawable.doctor_reone1
        "doctor_reone2.png" -> R.drawable.doctor_reone2
        "doctor_reone3.png" -> R.drawable.doctor_reone3
        "doctor_wanna1.png" -> R.drawable.doctor_wanna1
        else -> R.drawable.ic_launcher_background
    }
}

fun imageLocalMapperTmpSurgery(imageName: String): Int {

    return when (imageName) {
        "surgery_device_lifting.png" -> R.drawable.surgery_device_lifting
        "surgery_device_shrink.png" -> R.drawable.surgery_device_shrink
        "surgery_device_skinbooster.png" -> R.drawable.surgery_device_skinbooster
        "surgery_device_tuneface.png" -> R.drawable.surgery_device_tuneface
        "surgery_device_ulthera.png" -> R.drawable.surgery_device_ulthera
        "surgery_device_inmode.png" -> R.drawable.surgery_device_inmode
        "surgery_device_filler.png" -> R.drawable.surgery_device_filler
        "surgery_device_thermage.png" -> R.drawable.surgery_device_thermage
        "surgery_device_botox.png" -> R.drawable.surgery_device_botox
        else -> R.drawable.ic_launcher_background
    }
}