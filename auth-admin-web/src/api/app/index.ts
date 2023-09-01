import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { AppQuery, AppPageResult, AppForm } from "./types";

/**
 * 获取角色分页数据
 *
 * @param queryParams
 */
export function getAppPage(
  queryParams?: AppQuery
): AxiosPromise<AppPageResult> {
  return request({
    url: "/api/v1/app/page",
    method: "post",
    data: queryParams,
  });
}
