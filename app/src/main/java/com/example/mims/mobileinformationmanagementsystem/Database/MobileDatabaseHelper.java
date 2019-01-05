package com.example.mims.mobileinformationmanagementsystem.Database;


import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.mims.mobileinformationmanagementsystem.R;


public class MobileDatabaseHelper extends SQLiteOpenHelper {
    public MobileDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String mobile = "create table mobile(id integer primary key autoincrement ,Name varchar(20) unique,Time varchar(20),Country varchar(20),CEO varchar(20),Introduce text,Image text)";
        db.execSQL(mobile);
        Uri uri_nubia = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.nubia);
        ContentValues values = new ContentValues();
        //努比亚
        values.put("Name","努比亚");
        values.put("Time","2012-10-30");
        values.put("Country","中国");
        values.put("CEO","里强");
        values.put("Introduce","努比亚（nubia），新锐智能手机品牌，定位高端市场，立足中国面向全球，以“Be Yourself”为品牌理念。努比亚手机产品拥有诸多单反级摄影功能，被称为“可以拍星星的手机”。努比亚在业内推出了“全网通”，并通过搭载FiT交互技术的全球首款“无边框手机”nubia Z9为广大用户认可。世界足坛巨星C·罗纳尔多任努比亚品牌代言人。");
        values.put("Image", String.valueOf(uri_nubia));
        db.insert("mobile",null,values);
        values.clear();
        //华为
        Uri uri_huawei = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.huawei);
        values.put("Name","华为");
        values.put("Time","1987-09-15");
        values.put("Country","中国");
        values.put("CEO","任正非");
        values.put("Introduce","华为技术有限公司是一家生产销售通信设备的民营通信科技公司，于1987年正式注册成立，总部位于中国广东省深圳市龙岗区坂田华为基地。华为是全球领先的信息与通信技术（ICT）解决方案供应商，专注于ICT领域，坚持稳健经营、持续创新、开放合作，在电信运营商、企业、终端和云计算等领域构筑了端到端的解决方案优势，为运营商客户、企业客户和消费者提供有竞争力的ICT解决方案、产品和服务，并致力于实现未来信息社会、构建更美好的全联接世界。2013年，华为首超全球第一大电信设备商爱立信，排名《财富》世界500强第315位。");
        values.put("Image", String.valueOf(uri_huawei));
        db.insert("mobile",null,values);
        values.clear();
        //中兴
        Uri uri_zte = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.zte);
        values.put("Name","中兴");
        values.put("Time","1985-05-01");
        values.put("Country","中国");
        values.put("CEO","徐子阳");
        values.put("Introduce","中兴通讯股份有限公司（英语：ZTE Corporation，全称：Zhongxing Telecommunication Equipment Corporation），简称中兴通讯（ZTE）。全球领先的综合通信解决方案提供商，中国最大的通信设备上市公司。主要产品包括：2G/3G/4G/5G无线基站与核心网、IMS、固网接入与承载、光网络、芯片、高端路由器、智能交换机、政企网、大数据、云计算、数据中心、手机及家庭终端、智慧城市、ICT业务，以及航空、铁路与城市轨道交通信号传输设备。");
        values.put("Image", String.valueOf(uri_zte));
        db.insert("mobile",null,values);
        values.clear();
        //苹果
        Uri uri_apple = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.apple);
        values.put("Name","苹果");
        values.put("Time","1976-04-01");
        values.put("Country","美国");
        values.put("CEO","蒂姆·库克");
        values.put("Introduce","苹果公司（Apple Inc. ）是美国一家高科技公司。由史蒂夫·乔布斯、斯蒂夫·沃兹尼亚克和罗·韦恩(Ron Wayne)等人于1976年4月1日创立，并命名为美国苹果电脑公司（Apple Computer Inc. ），2007年1月9日更名为苹果公司，总部位于加利福尼亚州的库比蒂诺。");
        values.put("Image", String.valueOf(uri_apple));
        db.insert("mobile",null,values);
        values.clear();
        //酷派
        Uri uri_coolpad = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.coolpad);
        values.put("Name","酷派");
        values.put("Time","1993-04-10");
        values.put("Country","中国");
        values.put("CEO","张科");
        values.put("Introduce","酷派（Coolpad）是宇龙计算机通信科技（深圳）有限公司的手机品牌，创立于1993 年4月，是酷派集团有限公司（香港主板上市公司，股票代码 2369）的全资附属子公司，是中国专业的智能手机终端、移动数据平台系统、增值业务运营一体化解决方案提供商，专注于以智能手机为核心的无线数据一体化解决方案，并致力发展成为智能手机领导者与无线数据行业应用专家。");
        values.put("Image", String.valueOf(uri_coolpad));
        db.insert("mobile",null,values);
        values.clear();
        //三星
        Uri uri_samsung = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.samsung);
        values.put("Name","三星");
        values.put("Time","1938-03-01");
        values.put("Country","韩国");
        values.put("CEO","金玄石");
        values.put("Introduce","三星集团是韩国最大的跨国企业集团，同时也是上市企业全球500强，三星集团包括众多的国际下属企业，旗下子公司有：三星电子、三星物产、三星航空、三星人寿保险、雷诺三星汽车等，业务涉及电子、金融、机械、化学等众多领域。");
        values.put("Image", String.valueOf(uri_samsung));
        db.insert("mobile",null,values);
        values.clear();
        //小米
        Uri uri_mi = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.mi);
        values.put("Name","小米");
        values.put("Time","2010-03-03");
        values.put("Country","中国");
        values.put("CEO","雷军");
        values.put("Introduce","北京小米科技有限责任公司成立于2010年3月3日 ，是一家专注于智能硬件和电子产品研发的移动互联网公司，同时也是一家专注于高端智能手机、互联网电视以及智能家居生态链建设的创新型科技企业。");
        values.put("Image", String.valueOf(uri_mi));
        db.insert("mobile",null,values);
        values.clear();
        //索尼
        Uri uri_sony = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.sony);
        values.put("Name","索尼");
        values.put("Time","1946-05-06");
        values.put("Country","日本");
        values.put("CEO","平井一夫");
        values.put("Introduce","索尼是世界视听、电子游戏、通讯产品和信息技术等领域的先导者，是世界最早便携式数码产品的开创者，是世界最大的电子产品制造商之一、世界电子游戏业三大巨头之一、美国好莱坞六大电影公司之一。其旗下品牌有Xperia，Walkman，Sony music，哥伦比亚电影公司，PlayStation等。");
        values.put("Image", String.valueOf(uri_sony));
        db.insert("mobile",null,values);
        values.clear();
        //联想
        Uri uri_lenovo = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.lenovo);
        values.put("Name","联想");
        values.put("Time","1984-08-01");
        values.put("Country","中国");
        values.put("CEO","杨元庆");
        values.put("Introduce","联想集团是1984年中国科学院计算技术研究所投资20万元人民币，由11名科技人员创办，是中国的一家在信息产业内多元化发展的大型企业集团，和富有创新性的国际化的科技公司。");
        values.put("Image", String.valueOf(uri_lenovo));
        db.insert("mobile",null,values);
        values.clear();
        //诺基亚
        Uri uri_Nokia = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.nokia);
        values.put("Name","诺基亚");
        values.put("Time","1865-07-12");
        values.put("Country","芬兰");
        values.put("CEO","拉吉夫·苏里");
        values.put("Introduce","诺基亚公司（nokia Corporation）是一家总部位于芬兰埃斯波 ，主要从事移动通信设备生产和相关服务的跨国公司。诺基亚成立于1865年，以伐木、造纸为主业，逐步向胶鞋、轮胎、电缆等领域扩展。后发展成为一家手机制造商，以通信基础业务和先进技术研发及授权为主。");
        values.put("Image", String.valueOf(uri_Nokia));
        db.insert("mobile",null,values);
        values.clear();
        //摩托罗拉
        Uri uri_moto = Uri.parse("android.resource://com.example.mims.mobileinformationmanagementsystem/"+ R.drawable.moto);
        values.put("Name","摩托罗拉");
        values.put("Time","1928-06-20");
        values.put("Country","美国");
        values.put("CEO","格雷·布朗、桑杰·贾");
        values.put("Introduce","摩托罗拉（Motorola Inc ），原名：Galvin Manufacturing Corporation，成立于1928年。1947年，改名为Motorola，从1930年代开始作为商标使用。摩托罗拉总部设在美国伊利诺伊州绍姆堡，位于芝加哥市郊。世界财富百强企业之一，是全球芯片制造、电子通讯的领导者。");
        values.put("Image", String.valueOf(uri_moto));
        db.insert("mobile",null,values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
