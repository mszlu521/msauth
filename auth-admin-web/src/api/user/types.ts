/**
 * 登录用户信息
 */
export interface UserInfo {
  userId: number;
  nickname: string;
  avatar: string;
  roles: string[];
  perms: string[];
}

/**
 * 用户查询对象类型
 */
export interface UserQuery extends PageQuery {
  keywords?: string;
  status?: number;
  orgId?: number;
}

/**
 * 用户分页对象
 */
export interface UserPageVO {
  /**
   * 用户头像地址
   */
  avatar?: string;
  /**
   * 创建时间
   */
  createTime?: Date;
  /**
   * 组织名称
   */
  orgNames?: string[];
  /**
   * 用户邮箱
   */
  email?: string;
  /**
   * 性别
   */
  gender?: number;
  /**
   * 用户ID
   */
  id?: number;
  /**
   * 手机号
   */
  mobile?: string;
  /**
   * 用户昵称
   */
  name?: string;
  /**
   * 用户状态(1:启用;0:禁用)
   */
  status?: number;
}

/**
 * 用户表单类型
 */
export interface UserForm {
  /**
   * 用户头像
   */
  avatar?: string;
  /**
   * 组织ID
   */
  orgIds?: number[];
  /**
   * 邮箱
   */
  email?: string;
  /**
   * 性别
   */
  gender?: number;
  /**
   * 用户ID
   */
  id?: number;
  mobile?: string;
  /**
   * 昵称
   */
  name?: string;

  /**
   * 用户状态(1:正常;0:禁用)
   */
  status?: number;
}
