import Login from './views/Login.vue'
import Register from './views/itsource/Register.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import Table from './views/nav1/Table.vue'
import CourseType from './views/itsource/CourseType.vue'
import Department from './views/itsource/department.vue'
import TenantType from './views/itsource/TenantType.vue'
import Tenant from './views/itsource/Tenant.vue'
import Course from './views/itsource/Course.vue'
import Pager from './views/itsource/Pager.vue'
import Role from './views/itsource/role.vue'
import Form from './views/nav1/Form.vue'
import user from './views/nav1/user.vue'
import Page4 from './views/nav2/Page4.vue'
import Page5 from './views/nav2/Page5.vue'
import Page6 from './views/nav3/Page6.vue'
import echarts from './views/charts/echarts.vue'

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/register',
        component: Register,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '首页',
        leaf: true,//只有一个节点
        iconCls: 'fa fa-bar-chart',
        children: [
            { path: '/echarts', component: echarts, name: '首页' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '组织机构管理',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/department', component: Department, name: '部门管理' },
            { path: '/role', component: Role, name: '角色管理' }
        ]
    }
    ,
    {
        path: '/',
        component: Home,
        name: '系统基础设置',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: '/systemdictionary', component: Table, name: '数据字典' },
            { path: '/systemdictionaryitem', component: Table, name: '数据字典明细' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '课程相关',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/courseType', component: CourseType, name: '课程类型' },
            { path: '/course', component: Course, name: '课程管理' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '页面管理',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/pager', component: Pager, name: '静态化页面管理' },
            { path: '/pager', component: Pager, name: '站点管理' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '机构相关',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/tenantType', component: TenantType, name: '机构类型' },
            { path: '/tenant', component: Tenant, name: '机构管理' },
            { path: '/page5', component: Page5, name: '页面5' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '',
        iconCls: 'fa fa-address-card',
        leaf: true,//只有一个节点
        children: [
            { path: '/page6', component: Page6, name: '导航三' }
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;