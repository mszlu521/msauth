/**
 * 角色查询参数
 */
export interface AppQuery extends PageQuery {
  keywords?: string;
}

/**
 * 角色分页对象
 */
export interface AppPageVO {
  /**
   * 角色ID
   */
  id?: number;
  /**
   * 应用名称
   */
  name?: string;
  icon?: string;
  link?: string;
  /**
   * 认证类型
   */
  authType?: string;
  /**
   * 角色状态
   */
  status?: number;
  /**
   * 创建时间
   */
  createTime?: Date;
  /**
   * 修改时间
   */
  updateTime?: Date;
}

/**
 * 角色分页
 */
export type AppPageResult = PageResult<AppPageVO[]>;

/**
 * 角色表单对象
 */
export interface AppForm {
  id?: number;
  icon?: string;
  link?: string;
  name: string;
  authType?: number;
  /**
   * 状态(1-正常；0-停用)
   */
  status?: number;
}
