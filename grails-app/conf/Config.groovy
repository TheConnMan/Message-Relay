// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
	csv: 			'text/csv',
	pdf: 			'application/pdf',
	rtf: 			'application/rtf',
	excel: 			'application/vnd.ms-excel',
	ods: 			'application/vnd.oasis.opendocument.spreadsheet',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false
grails {
	mail {
		host='email-smtp.us-east-1.amazonaws.com'
		port=465
		props = [
			'mail.smtp.auth': 'true',
			'mail.smtp.socketFactory.port':'465',
			"mail.smtp.socketFactory.class": "javax.net.ssl.SSLSocketFactory",
			"mail.smtp.socketFactory.fallback": "false"
		]
	}
}

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
		grails.plugin.springsecurity.auth.loginFormUrl = '/saml/auth'
		grails.serverURL = "https://stb.srarad.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
	appenders {
		console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n'), threshold: org.apache.log4j.Level.INFO
		file name: 'file', layout:pattern(conversionPattern: '%d{dd-MM-yyyy HH:mm:ss} %p %c{2} - %m%n'), file: './ST.log', threshold: org.apache.log4j.Level.DEBUG
		file name: 'file2', layout:pattern(conversionPattern: '%d{dd-MM-yyyy HH:mm:ss} - %m%n'), file: './Activity.log', threshold: org.apache.log4j.Level.INFO
    }
	root {
		org.apache.log4j.Level.OFF
	}
	
	environments {
		development {
			info	stdout:	['com.sra', 'grails.app.conf'], additivity: false
		}
	}
    debug	file:	['com.sra', 'grails.app.conf'], additivity: false
    info	file2:	['com.sra.LoggingFilters'], additivity: false
	off 'org.grails.plugin.resource.ResourceMeta'
}

grails.app.context="/"

grails.plugin.springsecurity.providerNames = [
	'daoAuthenticationProvider',
	'anonymousAuthenticationProvider',
	'rememberMeAuthenticationProvider',
	'clientCredentialsAuthenticationProvider'
]

// SRA Plugins
grails.plugin.dbbackups.stem = 'sample'
grails.plugin.srasaml.appName = 'Single Tenant Base'

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.sra.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.sra.UserRole'
grails.plugin.springsecurity.authority.className = 'com.sra.Role'
grails.plugin.springsecurity.oauthProvider.active = true

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/**':								['permitAll'],
	'/searchable/**':					['ROLE_ADMIN'],
	'/user/**':							['ROLE_ADMIN'],
	'/role/**':							['ROLE_ADMIN'],
	'/registrationCode/**':				['ROLE_ADMIN'],
	'/securityInfo/**':					['ROLE_ADMIN'],
	'/oauth/authorize.dispatch':		['IS_AUTHENTICATED_REMEMBERED'],
    '/oauth/token.dispatch':			['IS_AUTHENTICATED_REMEMBERED']
]
