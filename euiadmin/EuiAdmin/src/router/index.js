import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [{
    path: '/',
    name: 'login',
    component: resolve => require(['@/views/login/Login.vue'], resolve),
    meta: {
      title: '登录',
      vist_label: ['super_admin', 'admin', 'user']
    },
  },
  {
    path: '/register',
    name: 'register',
    component: resolve => require(['@/views/login/Register.vue'], resolve),
    // component: () => import('@/views/login/Register.vue'),
    meta: {
      title: '注册',
      vist_label: ['super_admin', 'admin', 'user']
    },
  },
  {
    path: '/forget/password',
    name: 'forget_password',
    component: resolve => require(['@/views/login/ForgetPassword.vue'], resolve),
    // component: () => import('@/views/login/ForgetPassword.vue'),
    meta: {
      title: '找回密码',
      vist_label: ['super_admin', 'admin', 'user']
    },
  },
  {
    path: '/home',
    name: 'home',
    component: resolve => require(['@/views/home/Home.vue'], resolve),
    // component: () => import('@/views/home/Home.vue'),
    meta: {
      title: '主页',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/module/editor',
    name: 'editor',
    component: resolve => require(['@/views/module/Editor.vue'], resolve),
    // component: () => import('@/views/module/Editor.vue'),
    meta: {
      title: '富文本编辑器',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/module/superform',
    name: 'superform',
    component: resolve => require(['@/views/module/SuperForm.vue'], resolve),
    // component: () => import('@/views/module/SuperForm.vue'),
    meta: {
      title: '超级表单',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/module/upload/image',
    name: 'image',
    component: resolve => require(['@/views/module/UploadImage.vue'], resolve),
    // component: () => import('@/views/module/UploadImage.vue'),
    meta: {
      title: '图片上传',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/module/upload/file',
    name: 'file',
    component: resolve => require(['@/views/module/UploadFile.vue'], resolve),
    // component: () => import('@/views/module/UploadFile.vue'),
    meta: {
      title: '文件上传',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/module/animate',
    name: 'animate',
    component: resolve => require(['@/views/module/Animate.vue'], resolve),
    // component: () => import('@/views/module/Animate.vue'),
    meta: {
      title: 'Eui动画',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/module/table',
    name: 'table',
    component: resolve => require(['@/views/module/Table.vue'], resolve),
    // component: () => import('@/views/module/Table.vue'),
    meta: {
      title: '虚拟用户表格',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/module/table/excel',
    name: 'TableExcel',
    component: resolve => require(['@/views/module/TableExcel.vue'], resolve),
    // component: () => import('@/views/module/Table.vue'),
    meta: {
      title: '支持Excel表格',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  // Echarts表格
  {
    path: '/echarts/bar',
    name: 'echarts_bar',
    component: resolve => require(['@/views/echarts/EchartsBar.vue'], resolve),
    // component: () => import('@/views/echarts/EchartsBar.vue'),
    meta: {
      title: '柱状图',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/echarts/line',
    name: 'echarts_line',
    component: resolve => require(['@/views/echarts/EchartsLine.vue'], resolve),
    // component: () => import('@/views/echarts/EchartsLine.vue'),
    meta: {
      title: '折线图',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/echarts/map',
    name: 'echarts_map',
    component: resolve => require(['@/views/echarts/EchartsMap.vue'], resolve),
    // component: () => import('@/views/echarts/EchartsMap.vue'),
    meta: {
      title: '地图',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  // 常用页面
  {
    path: '/page/message/list',
    name: 'message_list',
    component: resolve => require(['@/views/page/MessageList.vue'], resolve),
    // component: () => import('@/views/page/MessageList.vue'),
    meta: {
      title: '留言',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/page/not/found',
    name: 'not_found',
    component: resolve => require(['@/views/page/NotFound.vue'], resolve),
    // component: () => import('@/views/page/NotFound.vue'),
    meta: {
      title: '页面不存在',
      login_state: false,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/page/warning',
    name: 'warning',
    component: resolve => require(['@/views/page/Warning.vue'], resolve),
    // component: () => import('@/views/page/Warning.vue'),
    meta: {
      title: '系统错误',
      login_state: false,
      vist_label: ['super_admin', 'admin']
    },
  },
  // 设置
  {
    path: '/setting/web',
    name: 'setting_seb',
    component: resolve => require(['@/views/setting/WebSetting.vue'], resolve),
    // component: () => import('@/views/setting/WebSetting.vue'),
    meta: {
      title: '系统设置',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/setting/email',
    name: 'setting_email',
    component: resolve => require(['@/views/setting/EmailSetting.vue'], resolve),
    // component: () => import('@/views/setting/EmailSetting.vue'),
    meta: {
      title: '邮箱设置',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/setting/user',
    name: 'setting_user',
    component: resolve => require(['@/views/setting/UserSetting.vue'], resolve),
    // component: () => import('@/views/setting/UserSetting.vue'),
    meta: {
      title: '个人设置',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/setting/password',
    name: 'setting_password',
    component: resolve => require(['@/views/setting/PasswordSetting.vue'], resolve),
    // component: () => import('@/views/setting/PasswordSetting.vue'),
    meta: {
      title: '密码修改',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
  {
    path: '/download',
    name: 'setting_password',
    component: resolve => require(['@/views/Download.vue'], resolve),
    // component: () => import('@/views/Download.vue'),
    meta: {
      title: '获取Eui',
      login_state: true,
      vist_label: ['super_admin', 'admin']
    },
  },
]

const router = new VueRouter({
  routes
})
router.beforeEach((to, from, next) => {
  let user_label = sessionStorage.getItem('user_label')
  let user_login = sessionStorage.getItem('user_login')
  let power = to.meta.vist_label.indexOf(user_label) //判断是否有权限
  let need_login = 'login_state' in to.meta //是否需要登录
  if (!need_login) {
    next()
    document.title = to.meta.title
  } else if (user_login == 'login' && power >= 0) {
    next()
    document.title = to.meta.title
  } else {
    alert('您未登录或权限不足')
    next({
      path: from.path
    })
  }
})

export default router