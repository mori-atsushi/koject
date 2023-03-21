import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        #if DEBUG
        let isTesting = CommandLine.arguments.contains("TESTING")
        KojectHelper.shared.startKoject(isTesting: isTesting)
        #else
        KojectHelper.shared.startKoject()
        #endif
    }
    
	var body: some Scene {
		WindowGroup {
            TodoListPage()
		}
	}
}
