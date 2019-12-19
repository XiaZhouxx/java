package cn.xz.testweb.mapper;

import cn.xz.bean.Clazz;
import cn.xz.bean.Partition;

/**
 * @author xz
 * @ClassName PartitionDao
 * @Description
 * @date 2019/4/11 0011 15:00
 **/
public interface PartitionDao{
    void add(Partition p);

    void addClass(Clazz c);

    void addCourse(Clazz c);

    void addMajor(Clazz c);
}
