package com.codingbot.shop.core.server

object StaticServerUrlPath {
    val imgOnlinePath_Prefix = "https://afsfroagcvtulsttshvc.supabase.co/storage/v1/object/public/hospitalstatic"
    val subpath_iamges_path = "/staticdata/images/hospitalimg/"
    val hospitalImgPath = imgOnlinePath_Prefix + subpath_iamges_path

    val hospitalImgUrlMaps = mapOf(
        "reone1" to "${hospitalImgPath}hospital_reone.png",
        "reone2" to "${hospitalImgPath}hospital_reone2.png",
        "reone3" to "${hospitalImgPath}hospital_reone3.png",
        "reone4" to "${hospitalImgPath}hospital_reone4.png",
        "chungdam_ace" to "${hospitalImgPath}hospital_chungdamace.png",
        "wanna" to "${hospitalImgPath}hospital_wanna_plastic_surgery.png",
        "youjins" to "${hospitalImgPath}hospital2_youjins.png",
        "hospital1" to "${hospitalImgPath}hospital1.png",
        "brillyn" to "${hospitalImgPath}hospital3_brillyn.png",
        "boss" to "${hospitalImgPath}hospital4_boss.png",
        "vline" to "${hospitalImgPath}hospital5_vline.png",
        )

}