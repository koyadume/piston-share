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
package in.koyad.piston.common.basic.exception;

public interface ErrorCodes {
	
	/* =============== context listener error codes ================= */
	//Classes with duplicate annotations found
	public static final String DUPLICATE_ANNOTATIONS = "DUPLICATE_ANNOTATIONS";

	//context error codes
	public static final String CONTEXT_ALREADY_INITIALIZED = "{0} is alreay initialized.";
	public static final String SERVER_CONTEXT_NOT_INITIALIZED = "SERVER_CONTEXT_NOT_INITIALIZED";
	
//	public static final String USER_CONTEXT_ALREADY_INITIALIZED = "USER_CONTEXT_ALREADY_INITIALIZED";
	public static final String USER_CONTEXT_NOT_INITIALIZED = "USER_CONTEXT_NOT_INITIALIZED";
	public static final String FWKCTX002 = "FWKCTX002";

	
	/* ============= Framework tag validation codes ============== */
	//Action not found
	public static final String FWKTV001 = "FWKTV001";

	
	/* ================= Tag handler codes ======================== */
	// Error while loading/initializing tag class
	public static final String FWKTH001 = "FWKTH001";

	
	/* ============= Framework rendering view codes ==================== */
	//Unable to write to response
	public static final String FWKRV001 = "FWKRV001";
    
	//Error while getting writer from response
	public static final String FWKRV002 = "FWKRV002";

	/* ============= Misc codes ==================== */
	//Invalid page url
	public static final String FWKMISC001 = "FWKMSC001";
	//Current tag should be instance of TileTag
	public static final String FWKMISC002 = "FWKMSC002";
	//Error while copying properties from one bean to another
	public static final String FWKMISC003 = "FWKMISC003";
	
	/* ============= PistonContext codes ==================== */
	//Error while setting tile
	public static final String FWKPC001 = "FWKPC001";
	//Error while resetting tile Id in view root
	public static final String FWKPC002 = "FWKPC002";
	
	/* ============= Piston session codes ==================== */
	//Session invalidation not allowed from a tile
	public static final String FWKPS001 = "FWKPC001";
	
	/* ============= Piston request codes ==================== */
	//Can't set attribute with this name.
	public static final String FWKPR001 = "FWKPR001";
	//Can't remove attribute with this name.
	public static final String FWKPR002 = "FWKPR002";

	/* ============== page builder ============== */
	//Error while creating page
	public static final String FWKPB001 = "FWKPB001";

	//multiple implementations found for SPI class
	public static final String FWKSPI001 = "FWKSPI001";

	public static final String FWKFLTR001 = "FWKFLTR001";

	/* =============== Cache ===================== */
	//Error while getting value from cache
	public static final String LOADING_CACHE_ERROR = "LOADING_CACHE_ERROR";

	/* ============ Portal page uri ================= */
	//Invalid portal page uri
	public static final String INVALID_PORTAL_PAGE_URI = "INVALID_PORTAL_PAGE_URI";

	public static final String AUTHENTICATION_REQUIRED = "AUTHENTICATION_REQUIRED";

	public static final String NO_ACCESS = "NO_ACCESS";

	public static final String CLOB_CONVERSION_ERROR = "CLOB_CONVERSION_ERROR";

	//No frame found for this site
	public static final String NO_SITE_FRAME_FOUND = "NO_SITE_FRAME_FOUND";

	//Error occured while loading portal page
	public static final String SITE_LOAD_ERROR = "PORTAL_PAGE_LOAD_ERROR";
	public static final String PORTAL_PAGE_LOAD_ERROR = "PORTAL_PAGE_LOAD_ERROR";
	public static final String PORTAL_PAGE_LAYOUT_LOAD_ERROR = "PORTAL_PAGE_LAYOUT_LOAD_ERROR";

	public static final String TAG_HANDLER_LOADING_ERROR = "TAG_HANDLER_LOADING_ERROR";

	public static final String ACTION_NOT_FOUND = "ACTION_NOT_FOUND";

	public static final String EXIT_CONTEXT_ERROR = "EXIT_CONTEXT_ERROR";

	public static final String COPY_REQ_PARAMS_ERROR = "COPY_REQ_PARAMS_ERROR";

	public static final String LDAP_CONNECTION_ERROR = "LDAP_CONNECTION_ERROR";

	public static final String LDAP_SEARCH_ERROR = "LDAP_SEARCH_ERROR";

	public static final String SQLFACTORY_NOT_INITIALIZED = "SQLFACTORY_NOT_INITIALIZED";
	
	public static final String EMFACTORY_NOT_INITIALIZED = "EMFACTORY_NOT_INITIALIZED";
	
	public static final String DUPLICATE_PREFERNCE = "DUPLICATE_PREFERNCE";
	
	public static final String INVALID_RESPONSE = "INVALID_RESPONSE";
	
	public static final String INVALID_OPERATION = "INVALID_OPERATION";

	public static final String NO_SUCH_FIELD = "NO_SUCH_FIELD";
	
}
