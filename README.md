EasyRecyclerView
=======
### **描述**

---------------
这是一个下拉刷新上拉加载更多框架,头部用的秋哥的[android-Ultra-Pull-To-Refresh](https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh),底部和没有数据的状态自己实现的.其实刚刚开始我是想找个库直接用的,试了几个排名靠前的,感觉跟自己想要的不太一样,索性自己写了一个,当然这当中也遇到了问题,多亏[仲锦大师](https://github.com/chenzj-king)的帮助在此感谢.

特点:
- 可定制的头部 (可以查看[android-Ultra-Pull-To-Refresh](https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh)文档)
- 可定制的底部 (加载中/没有数据/加载失败 三种状态的定制)
- 可定制的没有数据时候的显示 (目前只有一个状态 还在扩展)

### **效果预览**

---------------
![](http://of1ktyksz.bkt.clouddn.com/EasyRecyclerView.gif)

### **使用**

---------------
**1.使用默认头部和底部实现的EasyDefRecyclerView**

    <com.zly.www.easyrecyclerview.EasyDefRecyclerView
            android:id="@+id/erv"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:emply_layout="@layout/erv_default_empty" />

目前有三个自定义属性
- app:emply_layout 没有数据时候布局
- app:number_load_more 最后可见条目 + number_load_more > total 触发加载更多
- app:enable_load_more 是否允许加载更多


activity代码

    erv.setAdapter(rvAdapter = new RvAdapter());//设置adapter
    erv.setOnRefreshListener(this);//设置刷新监听
    erv.setOnLoadListener(this);//设置加载更多监听

adapter需要实现NormalAdapter或者MultipleAdapter抽象方法

    //创建ViewHolder
    public abstract VH createCustomViewHolder(ViewGroup parent, int viewType);
    //ViewHolder设置数据
    public abstract void bindCustomViewHolder(VH holder, T t, int position);

MultipleAdapter多条目布局还多一个方法需要实现

    //返回多条目的type
    public abstract int customItemViewType(int position);

adapter设置数据目前提供了这些方法具体实现可以在BaseAdapter中查看

新增数据
- public void addData(T t)
- public void addData(int position, T t)
- public void addDatas(List<T> list)
- public void addDatas(int position, List<T> list)

删除数据
- public void removeData(int position)
- public void removeData(T t)
- public void removeData(List<T> t)
- public void clearDatas()

修改数据
- public void setDatas(List<T> list)

查看数据
- public List<T> getDatas()

adapter中ViewHolder需要继承BaseViewHolder

**2.自定义**

头部使用秋哥的[android-Ultra-Pull-To-Refresh](https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh)这里可以参考秋哥的文档.

自定义底部需要实现ErvLoadUIHandle接口,可以参考ErvDefaultFooter

     public interface ErvLoadUIHandle {

        /**
         * 允许加载更多
         */
        int LOAD = 1;

        /**
         * 暂无更多数据
         */
        int NOMORE = 2;

        /**
         * 加载失败
         */
        int LOADFAIL = 3;

        /**
         * @return 获取底部当前状态
         */
        int getState();

        void onLoading();//loading状态实现

        void onNoMore();//没有数据状态实现

        void onLoadFail(OnLoadListener listener);//加载失败实现


    }

实现后调用setFooterView()方法设置

### **总结**
----
目前还在EasyRecyclerView还在优化欢迎各位提出你们宝贵的意见,例子可以参考Sample

### **感谢**
----
秋哥的[android-Ultra-Pull-To-Refresh](https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh)</br>
[仲锦大师](https://github.com/chenzj-king)的帮助

### **联系方式**
----
qq:1835556188</br>
blog:http://blog.csdn.net/zly921112</br>

### **License**
---
    Copyright (c) 2016 zhuliyuan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    Come on, don't tell me you read that.
