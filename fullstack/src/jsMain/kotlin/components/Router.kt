package dk.thrane.playground.components

import dk.thrane.playground.*
import org.w3c.dom.Element
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.events.Event

/*
inline fun NodeCursor<*>.routeLink(
    attrs: CommonAttributes<HTMLAnchorElement> = CommonAttributes(),
    href: String,
    children: (HTMLAnchorElement.() -> Unit) = {}
) {
    a(attrs, href) {
        children()

        on("click") { event ->
            event.preventDefault()
            Router.push(href)
        }
    }
}

object Router {
    data class RouteWithGenerator(val route: Route, val generator: Element.(segments: Map<String, String>) -> Unit)

    private val routes = ArrayList<RouteWithGenerator>()
    private lateinit var rootNode: Element
    private lateinit var currentRouteNode: Element
    private var notFoundRoute: Element.(segments: Map<String, String>) -> Unit = {
        div { text("Not found") }
    }

    fun mount(node: Element): Unit = with(node) {
        initializePopStateListener()
        rootNode = node
        mountRouteNode()
    }

    private fun mountRouteNode(): HTMLDivElement {
        val ref = Reference<HTMLDivElement>()
        rootNode.div(A(ref = ref))
        currentRouteNode = ref.current
        return ref.current
    }

    fun push(url: String) {
        window.history.pushState(null, "", url)
        refresh()
    }

    fun refresh() {
        val path = window.location.pathname
        val segments = path.split("/").filter { it.isNotEmpty() }

        val variables = HashMap<String, String>()
        var eligibleRoutes: List<RouteWithGenerator> = routes.filter { it.route.segments.size <= segments.size }
        segments.forEachIndexed { index, segment ->
            eligibleRoutes = eligibleRoutes.filter { (route, _) ->
                val routeSegment = route.segments.getOrNull(index)
                    ?: return@filter route.segments.lastOrNull() == RouteSegment.Remaining

                when (routeSegment) {
                    is RouteSegment.Plain -> routeSegment.segment == segment
                    is RouteSegment.Variable -> {
                        variables[routeSegment.name] = segment
                        true
                    }
                    RouteSegment.Remaining -> true
                    RouteSegment.Wildcard -> true
                }
            }
        }

        if (eligibleRoutes.size > 1) {
            console.warn("Found more than one eligible route!", eligibleRoutes)
            console.warn("The first route will be chosen")
        }

        val generator = eligibleRoutes.firstOrNull()?.generator ?: notFoundRoute
        if (generator == notFoundRoute) {
            println("Could not find route for: $path")
            console.log(routes)
        }

        deleteNode(currentRouteNode)
        mountRouteNode().generator(variables)
    }

    fun route(route: RouteBuilder.() -> Unit, children: Element.(segments: Map<String, String>) -> Unit) {
        routes.add(
            RouteWithGenerator(
                RouteBuilder().also(
                    route
                ).build(),
                children
            )
        )
    }

    private fun Element.initializePopStateListener() {
        val onPopState: (Event) -> Unit = { event: Event ->
            refresh()
        }

        window.addEventListener("popstate", onPopState)

        onDeinit {
            window.removeEventListener("popstate", onPopState)
        }
    }
}

fun Element.router(block: Router.() -> Unit) {
    Router.mount(this)
    Router.block()
    Router.refresh()
}

data class Route(val segments: List<RouteSegment>)

sealed class RouteSegment {
    data class Plain(val segment: String) : RouteSegment()
    data class Variable(val name: String) : RouteSegment()
    object Remaining : RouteSegment()
    object Wildcard : RouteSegment()
}

class RouteBuilder {
    private val segments = ArrayList<RouteSegment>()
    operator fun String.unaryPlus() {
        segments.add(RouteSegment.Plain(this))
    }

    operator fun RouteSegment.unaryPlus() {
        segments.add(this)
    }

    fun build(): Route {
        return Route(segments)
    }
}
 */