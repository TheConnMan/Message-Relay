package com.theconnman.relay.filters

import org.springframework.security.core.context.SecurityContextHolder

class LoggingFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
				log.info 'User: ' + SecurityContextHolder.getContext().getAuthentication().getName() + ', Controller: ' + controllerName + ', Action: ' + actionName
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
