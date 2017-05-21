/*
 * Copyright (c) 2012-2017 Shailendra Singh <shailendra_01@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.koyad.piston.common.basic.constant;

public interface FrameworkConstants {
	
	public static final String PISTON_ACTION_CONTROLLERS = "piston_action_controllers";
	public static final String PISTON_ACTIONS = "piston_actions";
	
	public static final String PISTON_TAGS = "piston_tags";
	
	public static final String PISTON_VIEW = "piston_view";
	
	//servlet mapping
	public static final String PISTON_PORTAL_PAGES = "pages";
	public static final String PISTON_FWK = "fwk";

	public static final String PISTON_TILE = "piston_tile";
	public static final String APP_JS_NAMESPACE = "app_js_namespace";
	
	public static final String PISTON_VIEW_ROOT = "piston_view_root";
	
	//namespace
//	public static final String HTML5_NAMESPACE = "http://piston.org/html5";
//	public static final String PORTAL_NAMESPACE = "http://piston.org/portal";
//	public static final String CORE_NAMESPACE = "http://piston.org/core";
	
	public static final String TAG_TILE = "tile";
	public static final String PISTON_START_ACTION = "piston_start_action";

	public static final String TAG_PLACE_HOLDER = "placeholder";

	public static final String PISTON_PLUGIN_ACTION = "piston_plugin_action";
	public static final String PISTON_PLUGIN_NEXTACTION = "piston_plugin_nextAction";
	public static final String PISTON_PLUGIN_FORM = "piston_plugin_form";
	

	public static final String PISTON_REDIRECT_URL = "piston_redirectURL";

	public static final String PISTON_PAGE_ID = "piston_page_id";
	public static final String PISTON_PAGE_TITLE = "piston_page_title";

	public static final String PISTON_SCOPE_CONTEXT = "piston_scope_context";
	public static final String PISTON_PAGE_STORE = "piston_page_store";
	public static final String PISTON_FWK_INITIALIZED = "piston_initialized";
	public static final String PISTON_ANNOTATION_REGISTRY = "piston_annotation_registry";
	public static final String PISTON_PAGE_MODE = "piston_page_mode";
	public static final String PISTON_PAGE_DOM = "piston_page_dom";
	public static final String PISTON_PAGE_METADATA = "piston_page_metadata";
	public static final String PISTON_CURRENT_PAGE = "piston_current_page";
	
	public static final String PISTON_ORIGNIAL_REQUEST_URI = "originalRequestURI";
	public static final String PISTON_REQUESTED_NODE = "piston_requested_node";
	public static final String PISTON_USER = "piston_user";
	public static final String PORTAL_SITE = "currentSite";
	public static final String PISTON_CURRENT_ITERATION = "piston_current_iteration";
	public static final String PISTON_FWK_PLUGINIDENTIFIER = "piston_fwk_pluginIdentifier";
	
//	public static final String GROUP_PORTAL_ADMINS = "portal-admins-group";
	public static final String ANONYMOUS_USER = "anonymous";
//	public static final String ENVIRONMENT = "environment";
//	
//	public static final String SCENARIO = "scenario";
	
	public static final String PISTON_APP_LISTENERS = "piston_app_listeners";	
	
	public static final String PISTON_TILE_ID = "piston_tile_id";
	public static final String PLUGIN_RES_PARENT_FOLDER = "appStatic";
	
	public static final String PREFIX_REDIRECT = "redirect:";
	public static final String PREFIX_FORWARD = "forward:";
	public static final String PREFIX_PLAIN = "plain:";
	/*
	 * As json string will have one or more ':" in it, we will use '$$' as separator
	 * otherwise split method will return incorrect json string.
	 */
	public static final String PREFIX_JSON = "json$$";

}