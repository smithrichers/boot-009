package com.example.boot009;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boot009.entity.User;
import com.example.boot009.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.annotation.Resource;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class Boot009ApplicationTests {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //测试乐观锁成功
    @Test
    public void testOptimisticLocker(){
        //查询用户信息
        User user = userMapper.selectById(1L);
        //修改用户信息
        user.setUsername("爸爸");
        //执行更新操作
        userMapper.updateById(user);

    }
    //测试乐观锁失败,多线程下
    @Test
    public void testOptimisticLocker_1(){
        //线程1
        User user = userMapper.selectById(1L);
        user.setUsername("爸爸啊");
        //线程2,模拟插队情况
        User user2 = userMapper.selectById(1L);
        user2.setUsername("爷爷2");
        userMapper.updateById(user2);
        userMapper.updateById(user);
    }
    //测试批量查询
    @Test
    public void selectCollect(){
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        userList.forEach(System.out::println);
    }
    //测试条件查询
    @Test
    public void selectBatchById(){
        HashMap<String, Object> map = new HashMap<>();
        //设置查询条件
        map.put("username","爷爷2");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);

    }
    //测试分页插件
    @Test
    public void page(){
        Page<User> page = new Page<>(1,5);//获取第一页，5个数据
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }
    //测试逻辑删除
    //一旦使用了逻辑删除，在其内置的crud操作中将无法看到deletec=1的数据,唯一的办法就是自己重写sql查询语句如@Select("select * from System where id = #{id}")
    @Test
    public void DeleteById(){
        userMapper.deleteById(1L);
    }

    /**
     * 获取数据库的所有表的名字
     */
    @Test
    public void sss(){
        // DataSource dataSource;
        List<Map<String, Object>> ll = jdbcTemplate.queryForList("select table_name from information_schema.tables where table_schema='library' and table_type='base table'");
        ll.forEach(t -> System.out.println(t));
    }
    @Test
    void testWrapper(){
        //查询name不为空，并且邮箱不为空的用户,年龄大于等于12
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
    @Test
    void  test2(){
        //查询名字等于狂神说的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","kaungshenshuo");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }
    @Test
    void test3(){
        //查询年龄在20到三十岁之间的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("age","20","30");
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println(count);
    }
    @Test
    //模糊查询
    void test4(){
        //查询name中不包含e，而且email中以t开头
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.notLike("name","e")
                .likeRight("email","t");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }
    @Test
        //模糊查询
    void test5(){
        //查询select deleted=0 and id in (select id from user where id<3)
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id","select id from user where id<3");
        List<Object> objs = userMapper.selectObjs(queryWrapper);
        objs.forEach(System.out::println);
    }
    @Test
        //排序
    void test6(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       //通过id进行排序
        queryWrapper.orderByDesc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


}
