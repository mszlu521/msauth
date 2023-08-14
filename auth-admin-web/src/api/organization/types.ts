export interface OrganizationVO {
  /**
   * 子
   */
  children?: OrganizationVO[];
  /**
   * 创建时间
   */
  createTime?: Date;
  /**
   * 组织ID
   */
  id?: number;
  /**
   * 组织名称
   */
  name?: string;
  /**
   * 组织代码
   */
  code?: string;
  /**
   * 父ID
   */
  parentId?: number;
  /**
   * 排序
   */
  sort?: number;
  /**
   * 状态(1:启用；0:禁用)
   */
  status?: number;
  /**
   * 修改时间
   */
  updateTime?: Date;
}

export interface OrganizationDTO {
  /**
   * 组织名称
   */
  name?: string;
  keywords?: string;
  status?: number;
}
