package com.codingbot.shop.core.server.jsondata

import com.codingbot.shop.core.server.InitValue

val HospitalDataJson =
    """
        {
          "hospitals": [
            {
              "id":1,
              "productName": "Re-One",
              "searchQuery": "리원피부과의원",                     
              "region": "${InitValue.LocationsData.LocationNameConst.APGUJEONG}",
              "images": ["hospital_reone.png", "hospital_reone2.png", "hospital_reone3.png", "hospital_reone4.png"],
              "surgeries": [1, 2, 3, 4, 5, 7, 8],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":2,
              "productName": "Chungdam Ace",
              "searchQuery": "청담에이스의원",
              "region": "${InitValue.LocationsData.LocationNameConst.APGUJEONG}",
              "images": ["hospital_chungdamace.png"],
              "surgeries": [1, 2, 5, 6, 8],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
            "id":3,
              "productName": "Wanna Plastic Surgery",
              "searchQuery": "워나성형외과의원",
              "region": "${InitValue.LocationsData.LocationNameConst.APGUJEONG}",
              "images": ["hospital_wanna_plastic_surgery.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1001,
              "productName": "Chungdam Oracle",
              "searchQuery": "청담오라클피부과의원",
              "region": "${InitValue.LocationsData.LocationNameConst.CHUNGDAM}",
              "images": ["hospital1.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1002,
              "productName": "Youjins",
                "searchQuery": "브이라인성형외과",
              "region": "${InitValue.LocationsData.LocationNameConst.GANGNAM}",
              "images": ["hospital2_youjins.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1003,
              "productName": "Brillyn",
            "searchQuery": "브릴린의원",
              "region": "${InitValue.LocationsData.LocationNameConst.APGUJEONG}",
              "images": ["hospital3_brillyn.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1004,
              "productName": "Boss",
            "searchQuery": "보스의원",
              "region": "${InitValue.LocationsData.LocationNameConst.HONGDAE}",
              "images": ["hospital4_boss.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1005,
              "productName": "Hospital-5",
            "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.SONGDO}",
              "images": ["hospital5_vline.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1006,
              "productName": "Hospital-6",
          "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.MYUNGDONG}",
              "images": ["hospital5_vline.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1007,
              "productName": "Hospital-7",
             "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.MYUNGDONG}",
              "images": ["hospital1.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1008,
              "productName": "Hospital-8",
           "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.APGUJEONG}",
              "images": ["hospital2_youjins.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1009,
              "productName": "Hospital-9",
          "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.APGUJEONG}",
              "images": ["hospital3_brillyn.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1010,
              "productName": "Hospital-10",
              "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.CHUNGDAM}",
              "images": ["hospital4_boss.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1011,
              "productName": "Hospital-11",
              "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.CHUNGDAM}",
              "images": ["hospital5_vline.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1012,
              "productName": "Hospital-12",
            "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.GANGNAM}",
              "images": ["hospital1.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1013,
              "productName": "Hospital-13",
          "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.GANGNAM}",
              "images": ["hospital2_youjins.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1014,
              "productName": "Hospital-14",
            "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.HONGDAE}",
              "images": ["hospital3_brillyn.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1015,
              "productName": "Hospital-15",
            "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.GANGNAM}",
              "images": ["hospital4_boss.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1016,
              "productName": "Hospital-16",
            "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.HONGDAE}",
              "images": ["hospital5_vline.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1017,
              "productName": "Hospital-17",
           "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.HONGDAE}",
              "images": ["hospital1.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1018,
              "productName": "Hospital-18",
           "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.MYUNGDONG}",
              "images": ["hospital2_youjins.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            },
            {
              "id":1019,
              "productName": "Hospital-19",
            "searchQuery": "청담포레브",
              "region": "${InitValue.LocationsData.LocationNameConst.MYUNGDONG}",
              "images": ["hospital3_brillyn.png"],
              "surgeries": [2, 3, 5, 6, 7],
              "wish": false,
              "cart": false,
              "order": 0
            }
          ]
        }
    """.trimIndent()