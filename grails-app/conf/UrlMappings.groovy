class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/api/$action/$function"(controller:'api')
		"/"(controller:"home")
		"/controllers"(view:"/index")
		"/health"(controller:"home",action:"health")
		"500"(view:'/error')
		"404"(view:'/404')
	}
}
