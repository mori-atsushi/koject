import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        #if DEBUG
        let isTesting = CommandLine.arguments.contains("TESTING")
        if isTesting {
            KojectHelper.shared.startTest()
        } else {
            KojectHelper.shared.start()
        }
        #else
        KojectHelper.shared.start()
        #endif
    }
    
	var body: some Scene {
		WindowGroup {
            TodoListPage()
		}
	}
}
