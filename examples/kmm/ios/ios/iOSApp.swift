import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        KojectHelperKt.startKoject()
    }
    
	var body: some Scene {
		WindowGroup {
            TodoList()
		}
	}
}
