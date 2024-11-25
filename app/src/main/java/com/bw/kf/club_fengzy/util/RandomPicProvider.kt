package com.bw.kf.club_fengzy.util

import kotlin.random.Random

object RandomPicProvider {

    private val PIC_LIST = arrayListOf(
        "https://pic.616pic.com/photoone/00/04/58/618ce9369d3a88735.jpg",
        "https://pic.616pic.com/photoone/00/05/38/618e239e085e59935.jpg",
        "https://pic.616pic.com/photoone/00/05/63/618e252d8b4969471.jpg",
        "https://pic.616pic.com/photoone/00/05/59/618e24e9c9d8f2292.jpg",
        "https://pic.616pic.com/photoone/00/04/55/618ce90b3b51f4831.jpg",
        "https://pic.616pic.com/photoone/00/04/67/618ce9c7ccc895793.jpg",
        "https://pic.616pic.com/photoone/00/04/70/618cea0220ef06654.jpg",
        "https://pic.616pic.com/photoone/00/03/07/618cf8317396c1247.jpg",
        "https://pic.616pic.com/photoone/00/02/83/618cf6ba1584f2441.jpg",
        "https://pic.616pic.com/photoone/00/02/78/618cf668acc077162.jpg",
        "https://pic.616pic.com/photoone/00/05/55/618e24b1cdbaf7374.jpg",
        "https://pic.616pic.com/photoone/00/03/98/618ce5661f56e46.jpg",
        "https://pic.616pic.com/photoone/00/01/98/618cf17817f909910.jpg",
        "https://pic.616pic.com/photoone/00/03/67/618ce35e78cd78974.jpg",
        "https://pic.616pic.com/photoone/00/05/65/618e254e77afe8449.jpg",
        "https://pic.616pic.com/photoone/00/03/01/618cf7ce4d58d7963.jpg",
        "http://img1.jiche.com//moto/741/o/78480ab7ea2eccef486efdd5d6ac3a13.jpg",
        "http://img3.jiche.com//moto/741/o/abea3ea07a38b0d7403630ff92e52c83.jpg",
        "http://img1.jiche.com//moto/741/o/78480ab7ea2eccef486efdd5d6ac3a13.jpg",
        "http://img3.jiche.com//moto/741/o/abea3ea07a38b0d7403630ff92e52c83.jpg",
        "http://img1.jiche.com//moto/741/o/78480ab7ea2eccef486efdd5d6ac3a13.jpg",
        "http://img3.jiche.com//moto/741/o/abea3ea07a38b0d7403630ff92e52c83.jpg",
        "http://img1.jiche.com//moto/741/o/78480ab7ea2eccef486efdd5d6ac3a13.jpg",
        "http://img3.jiche.com//moto/741/o/abea3ea07a38b0d7403630ff92e52c83.jpg",
    )

    private val CLUB_AVATAR = arrayListOf(
        "https://tupian.qqw21.com/article/UploadPic/2020-3/202032220515424474.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/202032220515565993.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/202032220515665316.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/202032220515650899.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/202032220515765580.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/202032220515821239.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/202032220515923234.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/20203222052052019.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/20203222052114491.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/20203222052150814.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/20203222052241593.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2020-3/20203222052321154.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2019-10/201910271154244367.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2019-10/201910271154481809.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2019-10/201910271154311506.jpg",
        "https://tupian.qqw21.com/article/UploadPic/2019-10/201910271154335145.jpg",
    )

    private val AVATAR_LIST = arrayListOf(
        "https://img2.woyaogexing.com/2022/08/03/cedd74e34423b7d7!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/03/711258c1f99a2f12!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/03/cec9ad1baef0d1f8!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/03/0cca1624684e02e8!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/03/7def2c57e24a1895!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/03/778edacce43fb14c!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/03/7b62a8a97d040ead!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/02/5f956b85d30eb8ba!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/02/31c755ebd5e53163!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/02/6c3ed7caa23fdc79!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/02/ed102816b14b150d!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/02/e5bc70c4f1b4adb5!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/01/a98b5bfd4cd3076e!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/02/3c856e420bd7486c!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/01/8865dd19cb65066d!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/01/a5a72916663ec9a3!400x400.jpg",
        "https://img2.woyaogexing.com/2022/08/01/5ac5a3e290c27a02!400x400.jpg"
    )

    fun pic() = PIC_LIST.random()

    fun avatar() = AVATAR_LIST.random()

    fun clubAvatar() = CLUB_AVATAR.random()

    fun randomClubAvatarList(): ArrayList<String> {
        val list = ArrayList<String>()
        val count = Random.nextInt(9) + 1
        for (i in 1..count)
            list.add(clubAvatar())
        return list
    }

    fun randomPicList(): ArrayList<String> {
        val list = ArrayList<String>()
        val count = Random.nextInt(9) + 1
        for (i in 1..count)
            list.add(pic())
        return list
    }
}