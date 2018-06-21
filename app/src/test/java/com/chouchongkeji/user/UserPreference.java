package com.chouchongkeji.user;

import com.chouchongkeji.App;
import com.chouchongkeji.dial.dao.user.GiftPreferenceDictMapper;
import com.chouchongkeji.dial.dao.user.UserTagDictMapper;
import com.chouchongkeji.dial.pojo.user.GiftPreferenceDict;
import com.chouchongkeji.dial.pojo.user.UserTagDict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class UserPreference {

    @Autowired
    private UserTagDictMapper userTagDictMapper;

    @Autowired
    private GiftPreferenceDictMapper giftPreferenceDictMapper;

    @Test
    public void addTagDict() {
        String tags = "外向，善交际，守秩序，细心，上进，有志向，白富美，高富帅，勤劳，聪明，言行一致，才华横溢，出口成章，勇敢，内向，不上进，无追求，自私，悲观，人缘差，矮矬穷，屌丝女，懒惰，表里不一，不合群";
        String[] strings = tags.split("，");
        int i = 0;
        for (String string : strings) {
            UserTagDict dict = new UserTagDict();
            dict.setTag(string);
            if (i < 14) {
                dict.setType((byte) 1);
            } else {
                dict.setType((byte) 2);
            }
            i++;
            userTagDictMapper.insert(dict);
        }
    }

    @Test
    public void addGiftReference() {
        String re = "数码，古玩，玉石，盗墓，书画，历史，诗词，琴棋";
        String[] split = re.split("，");
        for (String s : split) {
            GiftPreferenceDict dict = new GiftPreferenceDict();
            dict.setText(s);
            giftPreferenceDictMapper.insert(dict);
        }
    }

}
