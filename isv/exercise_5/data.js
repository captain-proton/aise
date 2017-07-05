/**
 * Created by nils on 30.06.17.
 */
var graph = {
    nodes: [
        {"id": "net.sf.jftp.CommandLine"},
        {"id": "net.sf.jftp.JFtp"},
        {"id": "net.sf.jftp.config.LoadSet"},
        {"id": "net.sf.jftp.config.SaveSet"},
        {"id": "net.sf.jftp.config.Settings"},
        {"id": "net.sf.jftp.event.Acceptor"},
        {"id": "net.sf.jftp.event.Event"},
        {"id": "net.sf.jftp.event.EventCollector"},
        {"id": "net.sf.jftp.event.EventHandler"},
        {"id": "net.sf.jftp.event.EventProcessor"},
        {"id": "net.sf.jftp.event.FtpEvent"},
        {"id": "net.sf.jftp.event.FtpEventConstants"},
        {"id": "net.sf.jftp.event.FtpEventHandler"},
        {"id": "net.sf.jftp.gui.AppMenuBar"},
        {"id": "net.sf.jftp.gui.AutoRemover"},
        {"id": "net.sf.jftp.gui.Creator"},
        {"id": "net.sf.jftp.gui.Dir"},
        {"id": "net.sf.jftp.gui.DirCanvas"},
        {"id": "net.sf.jftp.gui.DirCellRenderer"},
        {"id": "net.sf.jftp.gui.DirEntry"},
        {"id": "net.sf.jftp.gui.DirLister"},
        {"id": "net.sf.jftp.gui.DirPanel"},
        {"id": "net.sf.jftp.gui.Displayer"},
        {"id": "net.sf.jftp.gui.DownloadList"},
        {"id": "net.sf.jftp.gui.FtpHost"},
        {"id": "net.sf.jftp.gui.HostChooser"},
        {"id": "net.sf.jftp.gui.HostList"},
        {"id": "net.sf.jftp.gui.LoadPanel"},
        {"id": "net.sf.jftp.gui.LocalDir"},
        {"id": "net.sf.jftp.gui.NameChooser"},
        {"id": "net.sf.jftp.gui.PathChanger"},
        {"id": "net.sf.jftp.gui.Properties"},
        {"id": "net.sf.jftp.gui.RemoteCommand"},
        {"id": "net.sf.jftp.gui.RemoteDir"},
        {"id": "net.sf.jftp.gui.Remover"},
        {"id": "net.sf.jftp.gui.RemoverQuery"},
        {"id": "net.sf.jftp.gui.Renamer"},
        {"id": "net.sf.jftp.gui.ResumeDialog"},
        {"id": "net.sf.jftp.gui.SftpHostChooser"},
        {"id": "net.sf.jftp.gui.SmbHostChooser"},
        {"id": "net.sf.jftp.gui.StatusCanvas"},
        {"id": "net.sf.jftp.gui.StatusPanel"},
        {"id": "net.sf.jftp.gui.Template"},
        {"id": "net.sf.jftp.gui.TransferComponent"},
        {"id": "net.sf.jftp.gui.Updater"},
        {"id": "net.sf.jftp.gui.ZipFileCreator"},
        {"id": "net.sf.jftp.gui.framework.BorderPanel"},
        {"id": "net.sf.jftp.gui.framework.GUIDefaults"},
        {"id": "net.sf.jftp.gui.framework.HButton"},
        {"id": "net.sf.jftp.gui.framework.HFrame"},
        {"id": "net.sf.jftp.gui.framework.HImage"},
        {"id": "net.sf.jftp.gui.framework.HImageButton"},
        {"id": "net.sf.jftp.gui.framework.HPanel"},
        {"id": "net.sf.jftp.gui.framework.HPasswordField"},
        {"id": "net.sf.jftp.gui.framework.HTextField"},
        {"id": "net.sf.jftp.net.BasicConnection"},
        {"id": "net.sf.jftp.net.ConnectionHandler"},
        {"id": "net.sf.jftp.net.ConnectionListener"},
        {"id": "net.sf.jftp.net.DataConnection"},
        {"id": "net.sf.jftp.net.FilesystemConnection"},
        {"id": "net.sf.jftp.net.FtpClient"},
        {"id": "net.sf.jftp.net.FtpConnection"},
        {"id": "net.sf.jftp.net.FtpConstants"},
        {"id": "net.sf.jftp.net.FtpServer"},
        {"id": "net.sf.jftp.net.FtpServerSocket"},
        {"id": "net.sf.jftp.net.FtpURLConnection"},
        {"id": "net.sf.jftp.net.FtpURLStreamHandler"},
        {"id": "net.sf.jftp.net.JConnection"},
        {"id": "net.sf.jftp.net.SftpConnection"},
        {"id": "net.sf.jftp.net.SftpVerification"},
        {"id": "net.sf.jftp.net.SmbConnection"},
        {"id": "net.sf.jftp.net.Transfer"},
        {"id": "net.sf.jftp.util.LocalIO"},
        {"id": "net.sf.jftp.util.Log"},
        {"id": "net.sf.jftp.util.Log4JLogger"},
        {"id": "net.sf.jftp.util.Logger"},
        {"id": "net.sf.jftp.util.StringUtils"},
        {"id": "net.sf.jftp.util.SystemLogger"}
    ], links: [
        {
            "source": "net.sf.jftp.CommandLine", "target": "net.sf.jftp.event.EventHandler",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.CommandLine", "target": "net.sf.jftp.event.FtpEventConstants",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.JFtp", "target": "net.sf.jftp.util.Logger",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.event.EventCollector", "target": "net.sf.jftp.event.Acceptor",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.event.EventProcessor", "target": "net.sf.jftp.event.Acceptor",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.event.EventProcessor", "target": "net.sf.jftp.event.EventHandler",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.event.EventProcessor", "target": "net.sf.jftp.event.FtpEventConstants",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.event.FtpEvent", "target": "net.sf.jftp.event.Event",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.event.FtpEventHandler", "target": "net.sf.jftp.event.EventHandler",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.Creator", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.DirPanel", "target": "net.sf.jftp.gui.Dir",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.DirPanel", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.Displayer", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.DownloadList", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.HostChooser", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.LoadPanel", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.LocalDir", "target": "net.sf.jftp.gui.DirPanel",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.LocalDir", "target": "net.sf.jftp.net.ConnectionListener",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.NameChooser", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.PathChanger", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.Properties", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.RemoteCommand", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.RemoteDir", "target": "net.sf.jftp.gui.DirPanel",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.RemoteDir", "target": "net.sf.jftp.net.ConnectionListener",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.Remover", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.RemoverQuery", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.Renamer", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.ResumeDialog", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.SftpHostChooser", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.SmbHostChooser", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.StatusPanel", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.Template", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.gui.TransferComponent", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.net.FilesystemConnection", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.net.FtpConnection", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.net.FtpConnection", "target": "net.sf.jftp.net.FtpConstants",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.net.SftpConnection", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.net.SmbConnection", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.util.Log4JLogger", "target": "net.sf.jftp.util.Logger",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.util.SystemLogger", "target": "net.sf.jftp.util.Logger",
            "type": "CIG"
        },
        {
            "source": "net.sf.jftp.CommandLine", "target": "net.sf.jftp.event.Event",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.JFtp", "target": "net.sf.jftp.net.ConnectionHandler",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.event.Acceptor", "target": "net.sf.jftp.event.Event",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.event.EventCollector", "target": "net.sf.jftp.event.Event",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.event.EventHandler", "target": "net.sf.jftp.event.Event",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.event.EventProcessor", "target": "net.sf.jftp.event.Event",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.event.EventProcessor", "target": "net.sf.jftp.event.EventHandler",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.event.FtpEventHandler", "target": "net.sf.jftp.event.Event",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.AppMenuBar", "target": "net.sf.jftp.JFtp",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.Creator", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.Dir", "target": "net.sf.jftp.gui.DownloadList",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.Dir", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.DirLister", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.DirPanel", "target": "net.sf.jftp.gui.DownloadList",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.DirPanel", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.DownloadList", "target": "net.sf.jftp.gui.DirEntry",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.HostList", "target": "net.sf.jftp.gui.FtpHost",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.LocalDir", "target": "net.sf.jftp.gui.DirEntry",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.LocalDir", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.RemoteDir", "target": "net.sf.jftp.gui.DirEntry",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.RemoteDir", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.ResumeDialog", "target": "net.sf.jftp.gui.DirEntry",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.gui.StatusPanel", "target": "net.sf.jftp.JFtp",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.BasicConnection", "target": "net.sf.jftp.net.ConnectionListener",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.ConnectionHandler", "target": "net.sf.jftp.net.Transfer",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.ConnectionListener", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.DataConnection", "target": "net.sf.jftp.net.FtpConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.FilesystemConnection", "target": "net.sf.jftp.net.ConnectionListener",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.FtpConnection", "target": "net.sf.jftp.net.ConnectionHandler",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.FtpConnection", "target": "net.sf.jftp.net.ConnectionListener",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.FtpConnection", "target": "net.sf.jftp.net.DataConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.FtpURLConnection", "target": "net.sf.jftp.net.FtpConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.SftpConnection", "target": "net.sf.jftp.net.ConnectionListener",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.SmbConnection", "target": "net.sf.jftp.net.ConnectionListener",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.Transfer", "target": "net.sf.jftp.net.ConnectionHandler",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.Transfer", "target": "net.sf.jftp.net.DataConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.net.Transfer", "target": "net.sf.jftp.net.FtpConnection",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.util.Log", "target": "net.sf.jftp.util.Logger",
            "type": "CUG"
        },
        {
            "source": "net.sf.jftp.CommandLine", "target": "net.sf.jftp.event.EventCollector",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.JFtp", "target": "net.sf.jftp.gui.Dir",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.JFtp", "target": "net.sf.jftp.gui.DownloadList",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.JFtp", "target": "net.sf.jftp.gui.HostChooser",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.JFtp", "target": "net.sf.jftp.gui.StatusPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.event.FtpEventHandler", "target": "net.sf.jftp.net.FtpClient",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.AppMenuBar", "target": "net.sf.jftp.JFtp",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Creator", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Creator", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Creator", "target": "net.sf.jftp.gui.framework.HTextField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Creator", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.DirLister", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.DirPanel", "target": "net.sf.jftp.gui.DirEntry",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.DirPanel", "target": "net.sf.jftp.gui.DownloadList",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.DirPanel", "target": "net.sf.jftp.net.BasicConnection",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.DownloadList", "target": "net.sf.jftp.gui.framework.HImageButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.HostChooser", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.HostChooser", "target": "net.sf.jftp.gui.framework.HFrame",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.HostChooser", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.HostChooser", "target": "net.sf.jftp.gui.framework.HPasswordField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.HostChooser", "target": "net.sf.jftp.gui.framework.HTextField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.HostList", "target": "net.sf.jftp.gui.FtpHost",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.LocalDir", "target": "net.sf.jftp.gui.DirCanvas",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.LocalDir", "target": "net.sf.jftp.gui.framework.BorderPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.LocalDir", "target": "net.sf.jftp.gui.framework.HImageButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.NameChooser", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.NameChooser", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.NameChooser", "target": "net.sf.jftp.gui.framework.HTextField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.PathChanger", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.PathChanger", "target": "net.sf.jftp.gui.framework.HTextField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Properties", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Properties", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.RemoteCommand", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.RemoteCommand", "target": "net.sf.jftp.gui.framework.HTextField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.RemoteDir", "target": "net.sf.jftp.gui.DirCanvas",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.RemoteDir", "target": "net.sf.jftp.gui.framework.BorderPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.RemoteDir", "target": "net.sf.jftp.gui.framework.HImageButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Remover", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Remover", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Remover", "target": "net.sf.jftp.gui.framework.HTextField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.RemoverQuery", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Renamer", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Renamer", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.Renamer", "target": "net.sf.jftp.gui.framework.HTextField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.ResumeDialog", "target": "net.sf.jftp.gui.DirEntry",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.SftpHostChooser", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.SftpHostChooser", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.SftpHostChooser", "target": "net.sf.jftp.gui.framework.HPasswordField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.SftpHostChooser", "target": "net.sf.jftp.gui.framework.HTextField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.SmbHostChooser", "target": "net.sf.jftp.gui.framework.HButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.SmbHostChooser", "target": "net.sf.jftp.gui.framework.HPanel",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.SmbHostChooser", "target": "net.sf.jftp.gui.framework.HPasswordField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.SmbHostChooser", "target": "net.sf.jftp.gui.framework.HTextField",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.StatusPanel", "target": "net.sf.jftp.JFtp",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.StatusPanel", "target": "net.sf.jftp.gui.StatusCanvas",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.gui.StatusPanel", "target": "net.sf.jftp.gui.framework.HImageButton",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.net.ConnectionHandler", "target": "net.sf.jftp.net.FtpConnection",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.net.DataConnection", "target": "net.sf.jftp.net.FtpConnection",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.net.FtpClient", "target": "net.sf.jftp.net.FtpConnection",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.net.FtpConnection", "target": "net.sf.jftp.net.ConnectionHandler",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.net.FtpConnection", "target": "net.sf.jftp.net.DataConnection",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.net.FtpConnection", "target": "net.sf.jftp.net.JConnection",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.net.FtpURLConnection", "target": "net.sf.jftp.net.FtpConnection",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.net.Transfer", "target": "net.sf.jftp.net.ConnectionHandler",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.net.Transfer", "target": "net.sf.jftp.net.FtpConnection",
            "type": "CAG"
        },
        {
            "source": "net.sf.jftp.util.Log", "target": "net.sf.jftp.util.Logger",
            "type": "CAG"
        }
    ]
};
