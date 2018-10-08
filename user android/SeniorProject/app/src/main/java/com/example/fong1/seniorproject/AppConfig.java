package com.example.fong1.seniorproject;

import java.util.HashMap;

/**
 * Created by fong1 on 2/5/2018.
 */

public class AppConfig {
    // Server user login url
    public static String URL_LOGIN = "http://chineseisfun.000webhostapp.com/php/login.php";

    // Server user register url
    public static String URL_REGISTER = "http://chineseisfun.000webhostapp.com/php/register.php";

    public static String[] groupChoiceOne={"b","p","m","f","d","t","n","l","g","k","h","z","c","s","zh","ch","sh","r","j","q","x"};

    public static String[] getGroupChoiceTwo={"ā","ān","āng","āo","ē","ēi","ēn","ēr","ie","in","iu","ō","ong","ōu","u","un","ü","üe","wā",
            "wāi","wān","wāng","wēi","wō","wū","yā","yān","yāng","yāo","yī","yīng","yō","yōng","yōu","yū","yuān","yuē","yūn"};

    public static String[] getGroupChoiceThree={"cān","cán","cǎn","càn","fōu","fóu","fǒu","fòu",
            "gēi","géi","gěi","gèi","guāi","guái","guǎi","guài","qiāng","qiáng","qiǎng","qiàng"};

    public static String question1="Press the icon and choose the correct pinyin sound";

    public static HashMap<String,String> groupChoiceThree = new HashMap<String,String>();

    public static void GroupchoiceThree(){
    HashMap<String,String> map = new HashMap<String,String>();
        map.put("beng1","bēng");
        map.put("beng2","béng");
        map.put("beng3","běng");
        map.put("beng4","bèng");
        map.put("can1","cān");
        map.put("can2","cán");
        map.put("can3","cǎn");
        map.put("can4","càn");
        map.put("chou1","chōu");
        map.put("chou2","chóu");
        map.put("chou3","chǒu");
        map.put("chou4","chòu");
        map.put("dei1","dēi");
        map.put("dei2","déi");
        map.put("dei3","děi");
        map.put("dei4","dèi");
        map.put("fou1","fōu");
        map.put("fou2","fóu");
        map.put("fou3","fǒu");
        map.put("fou4","fòu");
        map.put("gei1","gēi");
        map.put("gei2","géi");
        map.put("gei3","gěi");
        map.put("gei4","gèi");
        map.put("guai1","guāi");
        map.put("guai2","guái");
        map.put("guai3","guǎi");
        map.put("guai4","guài");
        map.put("heng1","hēng");
        map.put("heng2","héng");
        map.put("heng3","hěng");
        map.put("heng4","hèng");
        map.put("lai1","lāi");
        map.put("lai2","lái");
        map.put("lai3","lǎi");
        map.put("lai4","lài");
        map.put("qiang1","qiāng");
        map.put("qiang2","qiáng");
        map.put("qiang3","qiǎng");
        map.put("qiang4","qiàng");

        groupChoiceThree.putAll(map);
    }

    public static String question2="Press the icon and choose the correct word sound";

    public static String[] Categoly21={"我","你","的","是","了","他","么","们","在","有","这","那","不","什","个","来","要","就","人",
            "里","会","没","她","吗","去","也","说","为","好","很","对","想","到","能","看","它","大","快"};

}
